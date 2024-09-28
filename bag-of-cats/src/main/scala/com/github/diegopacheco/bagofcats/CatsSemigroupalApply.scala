package com.github.diegopacheco.bagofcats

import cats.instances.option._ // for Semigroupal
import cats.syntax.apply._  // for tupled and mapN

object CatsSemigroupalApply extends App{

  val res = (Option(123), Option("abc")).tupled
  println(res)

  val res2 = (Option(123), Option("abc"), Option(true)).tupled
  println(res2)

  final case class Cat(name: String, born: Int, color: String)

  val res3 = (
    Option("Garfield"),
    Option(1978),
    Option("Orange & black")
   ).mapN(Cat.apply)
  println(res3)

  val add: (Int, Int, Int) => Int = (a, b, c) => a + b + c
  val res4 = (Option(1), Option(2), Option(3)).mapN(add)
  println(res4)


}
