package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

object EvalMain extends App {
  
  import cats.Eval
  import cats.implicits._

  val eager = Eval.now {
    println("Running expensive calculation...")
    1 + 2 * 3
  }
  println(eager.value)
  
  val lazyEval = Eval.later {
    println("Running expensive calculation... LATER")
    1 + 2 * 3 + 2
  }
  println(lazyEval.value)
  
}