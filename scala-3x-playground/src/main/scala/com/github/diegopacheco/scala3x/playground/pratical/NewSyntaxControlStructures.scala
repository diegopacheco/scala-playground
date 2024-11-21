package com.github.diegopacheco.scala3x.playground.pratical

object NewSyntaxControlStructures extends App:
  val x = if 42 > 0 then "Positive" else "Negative"
  println(x)

  val y = for
    i <- 1 to 3
    j <- 1 to 3
  yield (i, j)
  println(y)
