package com.github.diegopacheco.sccalaplayground.monix

import monix.execution.CancelableFuture

object TaskApp extends App {

  implicit val scheduler = monix.execution.Scheduler.global
  import monix.eval.Task

  val task: Task[String] =
    Task.eval {
      println("effect")
      "Result"
    }

  val eventualResult: CancelableFuture[String] = task.runToFuture
  eventualResult.onComplete( s => println(s"Done $s") )

}
