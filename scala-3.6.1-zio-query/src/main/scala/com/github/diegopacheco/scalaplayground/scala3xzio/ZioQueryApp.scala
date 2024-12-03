package com.github.diegopacheco.scalaplayground.scala3xzio

import zio._
import zio.query._
import zio.stream._

object ZioQueryApp extends ZIOAppDefault {
  case class ProcessBatch(batch: Chunk[Int]) extends Request[Throwable, Unit]

  lazy val BatchDataSource: DataSource.Batched[Any, ProcessBatch] =
    new DataSource.Batched[Any, ProcessBatch] {
      val identifier: String = "BatchDataSource"

      def run(requests: Chunk[ProcessBatch])(implicit trace: Trace): ZIO[Any, Nothing, CompletedRequestMap] =
        requests.toList match {
          case request :: Nil =>
            val result: Task[Unit] = {
              // process single batch
              ZIO.succeed(println(s"Processing batch: ${request.batch}"))
            }

            result.exit.map(CompletedRequestMap.single(request, _))

          case batch: Seq[ProcessBatch] =>
            val result: Task[Unit] = {
              // process multiple batches
              ZIO.succeed(batch.foreach(
                req => println(s"Processing batch: ${req.batch}")))
            }

            result.foldCause(
              CompletedRequestMap.failCause(requests, _),
              _ => CompletedRequestMap.empty
            )
        }
    }

  def processBatch(batch: Chunk[Int]): ZQuery[Any, Throwable, Unit] =
    ZQuery.fromRequest(ProcessBatch(batch))(BatchDataSource)

  def run: ZIO[Any, Nothing, ExitCode] = {
    val data = (1 to 1000).toList
    val batchSize = 10

    val query: ZIO[Any, Throwable, Unit] =
      ZStream
        .fromIterable(data)
        .take(100)
        .grouped(batchSize)
        .mapZIOPar(4)(chunk => processBatch(chunk).run)
        .runDrain

    query.exitCode
  }
}