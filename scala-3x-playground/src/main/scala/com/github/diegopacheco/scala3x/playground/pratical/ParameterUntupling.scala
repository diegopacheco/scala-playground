package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/other-new-features/parameter-untupling.html
//
// Parameter Untupling
//
// In scala 2x. had to do case with pattern matcher:
// xs map {
//  case (x, y) => x + y
// }
// now we can do this:
//
object ParameterUntupling extends App:

  val xs: List[(Int, Int)] = List((1, 2), (3, 4))
  val ys: List[Int] = xs.map(
    (x, y) => x + y
  )
  println(ys)
