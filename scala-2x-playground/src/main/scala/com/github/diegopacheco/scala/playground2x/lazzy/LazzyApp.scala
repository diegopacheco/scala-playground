package com.github.diegopacheco.scala.playground2x.lazzy

//
// Careful: Potential Deadlock When Accessing lazy vals
//
object Holder {
  //
  // It evaluates the variable only on its first access.
  // Whenever we access this val at a later stage, no execution happens, and the compiler returns the result.
  //
  lazy val x = {
    println("Evaluating x")
    42
  }
}

object LazzyApp extends App{
  import Holder._

  println("Before x")
  println(x)
  println("After x")
  println(x)
  println(x)

}
