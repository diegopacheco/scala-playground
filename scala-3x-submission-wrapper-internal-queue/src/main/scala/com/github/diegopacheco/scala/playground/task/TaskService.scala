package com.github.diegopacheco.scala.playground.task

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newFixedThreadPool
import java.util.concurrent.atomic.AtomicInteger

class TaskService {

  private val esMain:ExecutorService = newFixedThreadPool(1)
  private val esTask:ExecutorService = newFixedThreadPool(1)
  private val count:AtomicInteger = new AtomicInteger(0)

  def submitTask(t:WorkTask):Unit = {
    val id = count.addAndGet(1)
    val adapter = new TaskAdapter(t, esTask, id)

    esMain.submit(adapter)
    println(s"Task ID ${id} submitted.")
  }

}
