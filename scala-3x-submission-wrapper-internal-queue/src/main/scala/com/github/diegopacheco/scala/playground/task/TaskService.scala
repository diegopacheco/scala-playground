package com.github.diegopacheco.scala.playground.task

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newFixedThreadPool
import java.util.concurrent.atomic.AtomicInteger

class TaskService {

  private val es:ExecutorService = newFixedThreadPool(2)
  private val count:AtomicInteger = new AtomicInteger(0)

  def submitTask(t:WorkTask):Unit = {
    val id = count.addAndGet(1)
    val adapter = new TaskAdapter(t, es, id)

    es.submit(adapter)
    println(s"Task ID ${id} submitted.")
  }

}
