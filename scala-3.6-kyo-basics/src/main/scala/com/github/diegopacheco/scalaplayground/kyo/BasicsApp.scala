package com.github.diegopacheco.scalaplayground.kyo

import kyo.*


object BasicsApp extends App{

  // An 'Int' is also an 'Int < Any'
  private val x: Int < Any = 1

  // Since there are no pending effects,
  // the computation can produce a pure value
  private val result: Int = x.eval
  println(s"Result: ${result}")

  val a: Int < Abort[Exception] = 42
  val b: Result[Exception, Int] < Any = Abort.run(a)
  // Retrieve pure value as there are no more pending effects
  val c: Result[Exception, Int] = b.eval
  println(s"Result: ${c}")

}
