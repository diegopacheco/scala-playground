package com.github.diegopacheco.scalaplayground.scala3xzio

import zio.*
import zio.jdbc.{JdbcDecoder, JdbcEncoder, SqlFragment, ZConnectionPool, ZConnectionPoolConfig, sqlInterpolator, transaction}
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

object ZioJdbcApp extends ZIOAppDefault with JsonSupport {

  val properties = Map(
    "user" -> "postgres",
    "password" -> "pass"
  )

  val connectionPool: ZLayer[ZConnectionPoolConfig, Throwable, ZConnectionPool] =
    ZConnectionPool.postgres("localhost", 5433, "vending", properties)

  val createZIOPoolConfig: ULayer[ZConnectionPoolConfig] =
    ZLayer.succeed(ZConnectionPoolConfig.default)

  val sqlFragmProducts: SqlFragment = sql"select id, name, stock from products"

  val productsSQL: ZIO[ZConnectionPool, Throwable, Chunk[(Int, String, Int)]] =
    transaction {
      sqlFragmProducts.query[(Int, String, Int)].selectAll
    }

  val program: ZIO[ZConnectionPool, Throwable, Chunk[Product]] = for {
    res <- productsSQL
  } yield res.map {
    case (id, name, stock) => Product(id, name, stock)
  }

  override def run: ZIO[ZIOAppArgs with Scope, Any, Any] =
    for {
      results <- program.provideLayer(createZIOPoolConfig >>> connectionPool)
      _ <- Console.printLine(results.mkString("\n"))
    } yield ()

}