package com.github.diegopacheco.bagofcats

import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.instances.invariant._ // for Semigroupal
import cats.instances.list._ // for Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.apply._ // for imapN
import cats.syntax.semigroup._ // for |+|

object CatsSemigrupalFunctorApply extends App{

  final case class Cat(
    name: String,
    yearOfBirth: Int,
    favoriteFoods: List[String]
  )

  val tupleToCat: (String, Int, List[String]) => Cat =
    Cat.apply _

  val catToTuple: Cat => (String, Int, List[String]) =
    cat => (cat.name, cat.yearOfBirth, cat.favoriteFoods)

  implicit val catMonoid: Monoid[Cat] = (
    Monoid[String],
    Monoid[Int],
    Monoid[List[String]]
  ).imapN(tupleToCat)(catToTuple)

  val garfield = Cat("Garfield", 1978, List("Lasagne"))
  val heathcliff = Cat("Heathcliff", 1988, List("Junk Food"))
  println(catMonoid.combine(garfield, heathcliff))
  println(catMonoid.combine(garfield, tupleToCat("Garfield", 1978, List("Lasagne"))))

  val res = garfield |+| heathcliff
  println(res)

}
