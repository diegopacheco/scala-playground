package com.github.diegopacheco.bagofcats

/**
 * Semigroup is anything that can be combined(combine). Cannot be empty.
 */
object CatsSemiGroups extends App {
  import cats.Semigroup
  val semigroup = Semigroup[String].combine("Hi ", "there")
  println(semigroup)
  println(Semigroup[String].combine("", ""))
}
