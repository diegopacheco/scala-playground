package com.github.diegopacheco.scala.playground.task

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newFixedThreadPool;

class TaskService {

  private val es:ExecutorService = newFixedThreadPool(1);

  def submitTask(t:WorkTask):Unit = {
    val adapter = new TaskAdapter(t)
    es.submit(adapter);
    println(s"Task ${t} submitted.")
  }

}
