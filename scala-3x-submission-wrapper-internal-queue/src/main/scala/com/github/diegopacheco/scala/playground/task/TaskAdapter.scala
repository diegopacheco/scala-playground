package com.github.diegopacheco.scala.playground.task

import java.util.concurrent.{Executors, TimeUnit, TimeoutException}

class TaskAdapter(val workTask:WorkTask) extends Runnable {

  private val executor = Executors.newSingleThreadExecutor()

  override def run(): Unit = {
    println(">> Running on TaskAdapter in 2s will timeout...")

    val futureTask = executor.submit(new Runnable {
      override def run(): Unit = {
        println(">> Waiting 1s before running the task...")
        Thread.sleep(1000)
        workTask.execute()
      }
    })

    try {
      futureTask.get(2, TimeUnit.SECONDS)
    } catch {
      case e: TimeoutException =>
        println(s"Task timed out")
        futureTask.cancel(true)
        executor.shutdownNow()
    }
  }

}
