package com.github.diegopacheco.scala3.basic.loops

object ForExpression extends App {
  val list =
    for i <- 10 to 12
      yield i * 2
  println(list)

  // we can do in 1 line too
  println(for i <- 10 to 12 yield i * 2)

  val result = (20 to 26).map(i => i * 2)
  println(result)

}
