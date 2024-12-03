package com.github.diegopacheco.scalaplayground.scala3xzio

import zio.*
import zio.query.*
import zio.jdbc.*
import zio.schema.{Schema, TypeId}

case class Product(id: Int, name: String, stock: Int)

object Product {

  import Schema.Field

  implicit val schema: Schema[Product] =
    Schema.CaseClass3[Int, String, Int, Product](
      TypeId.parse("com.github.diegopacheco.scalaplayground.scala3xzio.Product"),
      Field("id", Schema[Int], get0 = (p: Product) => p.id, set0 = (p, v) => p.copy(id = v)),
      Field("name", Schema[String], get0 = (p: Product) => p.name, set0 = (p, v) => p.copy(name = v)),
      Field("stock", Schema[Int], get0 = (p: Product) => p.stock, set0 = (p, v) => p.copy(stock = v)),
      (id, name, stock) => Product(id, name, stock)
    )
}

trait JsonSupport {
  implicit val productSchema: Schema[Product] = Product.schema
  implicit val jdbcDecoder: JdbcDecoder[Product] = JdbcDecoder.fromSchema
  implicit val jdbcEncoder: JdbcEncoder[Product] = JdbcEncoder.fromSchema
}

object ZQueryAppDB extends ZIOAppDefault with JsonSupport {

  case class GetProductName(id: Int) extends Request[Throwable, String]

  private val properties = Map(
    "user" -> "postgres",
    "password" -> "pass"
  )
  private val connectionPool: ZLayer[ZConnectionPoolConfig, Throwable, ZConnectionPool] =
    ZConnectionPool.postgres("localhost", 5433, "vending", properties)

  private val connectionPoolConfig: ZLayer[Any, Nothing, ZConnectionPoolConfig] =
    ZLayer.succeed(ZConnectionPoolConfig.default)

  lazy val ProductDataSource: DataSource.Batched[Any, GetProductName] =
    new DataSource.Batched[Any, GetProductName] {
      val identifier: String = "ProductDataSource"

      def run(requests: Chunk[GetProductName])(implicit trace: Trace): ZIO[Any, Nothing, CompletedRequestMap] =
        requests.toList match {
          case request :: Nil =>
            val result: Task[String] = {
              println(s"1. Request: $request")
              val query = sql"SELECT name FROM products WHERE id = ${request.id}".query[String]
              val productSQL = transaction {
                query.selectAll
              }
              productSQL.provideLayer(connectionPoolConfig >>> connectionPool).map(_.headOption.getOrElse("No result"))
            }
            result.exit.map(CompletedRequestMap.single(request, _))

          case batch: Seq[GetProductName] =>
            println(s"2. Request: Batch")

            val ids = batch.map(_.id)
            println(s"got IDS: $ids")

            val result: Task[List[(Int, String)]] = {
              val query = {
                val idsFragment = ids.map(id => sql"$id").reduceLeft(_ ++ sql", " ++ _)
                sql"SELECT id, name FROM products WHERE id IN (" ++ idsFragment ++ sql")"
              }.query[(Int, String)]
              println(s"Postgres Query: ${query.sql.toString}")

              val productSQL = transaction {
                query.selectAll.map(_.toList).tap(result => ZIO.succeed(println(s"Query Result: $result")))
              }

              for {
                startTime <- ZIO.succeed(java.lang.System.currentTimeMillis())
                result <- productSQL.provideLayer(connectionPoolConfig >>> connectionPool)
                endTime <- ZIO.succeed(java.lang.System.currentTimeMillis())
                duration = endTime - startTime
                _ <- ZIO.succeed(println(s"Query execution time: $duration ms"))
              } yield result
            }

            result.foldCause(
              cause => {
                println(s"Failed with cause: $cause")
                CompletedRequestMap.failCause(requests, cause)
              },
              CompletedRequestMap.fromIterableWith(_)(kv => GetProductName(kv._1), kv => Exit.succeed(kv._2))
            )
        }
    }

  private def getProductNameById(id: Int): ZQuery[Any, Throwable, String] =
    ZQuery.fromRequest(GetProductName(id))(ProductDataSource)

  private val query: ZQuery[Any, Throwable, List[String]] =
    for {
      ids <- ZQuery.succeed(1 to 10)
      _ = println(s"query IDS: $ids")

      names <- ZQuery.foreachPar(ids)(id => getProductNameById(id)).map(_.toList)

    } yield names

  def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    val program = for {
      productNames <- query.run
    } yield ()
    program.provideLayer(connectionPoolConfig >>> connectionPool).exitCode
  }

}