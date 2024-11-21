package com.github.diegopacheco.scala3x.playground.pratical

//
// Extension methods allow you to add new methods to existing types.
// Ruby Feelings
//
object NumberOps {
  extension (i: Int)
    def times: String = ("" + i)*i
}

object Extensions extends App:
  import NumberOps.*

  println(3.times)
