package com.github.diegopacheco.bagofcats

object CatsMonoids extends App{

  import cats.Monoid
  import cats.instances.string._ // for Monoid

  val result = Monoid[String].combine("Hello ", "Cats World!")
  println(result)

  val empty = Monoid[String].empty
  println(empty)

}
