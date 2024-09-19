package com.github.diegopacheco.bagofcats

/**
 * Monoid is anything that can be combined(combine) and has an empty value.
 * Monoid is a Semigroup with an empty value.
 */
object CatsMonoids extends App{

  import cats.Monoid
  import cats.instances.string._ // for Monoid

  val result = Monoid[String].combine("Hello ", "Cats World!")
  println(result)

  val empty = Monoid[String].empty
  println(empty)

}
