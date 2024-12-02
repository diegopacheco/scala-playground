package com.github.diegopacheco.scalaplayground.scala3xzio

import zio._
import zio.stream._

object BatchProcessing extends ZIOAppDefault {
  def processBatch(batch: Chunk[Int]): UIO[Unit] =
    ZIO.succeed(println(s"Processing batch: $batch"))

  def run: ZIO[Any, Nothing, ExitCode] = {
    val data = (1 to 100).toList
    val batchSize = 10

    ZStream
      .fromIterable(data)
      .grouped(batchSize)
      .mapZIOPar(4)(chunk => processBatch(chunk))
      .runDrain
      .exitCode
  }
}