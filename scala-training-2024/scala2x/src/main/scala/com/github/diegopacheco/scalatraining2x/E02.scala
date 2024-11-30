package com.github.diegopacheco.scalatraining2x

/*
* Using high order functions implement a benchmark function
* where you pass the function that executes the code and
* return another function that lazily computes the benchmark.
* */
object E02 {

  def benchmarkMS(f: => Unit): () => Long = {
    val start = System.currentTimeMillis()
    f
    val end = System.currentTimeMillis()
    () => end - start
  }
}

object E02App extends App {
  val lazyBench = E02.benchmarkMS {
    Thread.sleep(1000)
  }
  println(lazyBench())
}