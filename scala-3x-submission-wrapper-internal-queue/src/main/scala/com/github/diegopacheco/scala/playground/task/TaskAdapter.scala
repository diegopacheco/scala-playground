package com.github.diegopacheco.scala.playground.task

import java.util.concurrent.{ExecutorService, TimeUnit, TimeoutException}

class TaskAdapter(
  val workTask:WorkTask,
  val executor:ExecutorService,
  val id:Int) extends Runnable {

  override def run(): Unit = {
    println(s">> Running on TaskAdapter for task(${id}) in 2s will timeout...")

    val futureTask = executor.submit(new Runnable {
      override def run(): Unit = {
        println(">> Waiting 1s for task(${id}) before running")
        Thread.sleep(1000)
        workTask.execute()
      }
    })

    try {
      futureTask.get(2, TimeUnit.SECONDS)
    } catch {
      case e: TimeoutException =>
        println(s"Task timed out - for task(${id})")
        futureTask.cancel(true)
    }
  }

}
