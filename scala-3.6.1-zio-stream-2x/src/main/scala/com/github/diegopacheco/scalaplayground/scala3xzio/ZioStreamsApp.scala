package com.github.diegopacheco.scalaplayground.scala3xzio

import zio._
import zio.stream._

object ZioStreamsApp extends ZIOAppDefault {
  private def processBatch(batch: Chunk[Int]): UIO[Unit] =
    ZIO.succeed(println(s"Processing batch: $batch"))

  def run: ZIO[Any, Nothing, ExitCode] = {
    val data = (1 to 1000).toList
    val batchSize = 10

    ZStream
      .fromIterable(data)
      .take(100)
      .grouped(batchSize)
      .tap(x => ZIO.succeed(println(s"before mapping: $x")))
      .mapZIOPar(4)(chunk => processBatch(chunk))
      .runDrain
      .exitCode
  }
}