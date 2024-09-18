package com.github.diegopacheco.bagofcats

object CatsSemiGroups extends App {
  import cats.Semigroup
  val semigroup = Semigroup[String].combine("Hi ", "there")
  println(semigroup)
  println(Semigroup[String].combine("", ""))
}
