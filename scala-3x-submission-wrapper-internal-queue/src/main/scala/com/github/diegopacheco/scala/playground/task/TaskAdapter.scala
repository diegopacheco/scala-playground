package com.github.diegopacheco.scala.playground.task

import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt

class TaskAdapter(val workTask:WorkTask) extends Runnable {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  override def run(): Unit = {
    println(">> Running on TaskAdapter in 2s will timeout...")
    try {
      Await.result(
        Future {
          println(">> Waiting 1s before running the task...")
          Thread.sleep(1000)
          workTask.execute()
        },
      2.second)
    } catch {
      case e: java.util.concurrent.TimeoutException =>
        println(s"Task timed out: ${e.getMessage}")
    }
  }

}
