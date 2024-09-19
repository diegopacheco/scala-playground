package com.github.diegopacheco.bagofcats

import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.semigroup._ // for |+|
import cats.instances.int._    // for Monoid
import cats.instances.option._ // for Monoid
import cats.instances.map._
import cats.instances.tuple._

/**
 * IMHO is a bit o a trap, I would stick to traditional method names.
 */
object SemiGroupsMonoidsSyntax extends App{

  val result = "Scala" |+| " with " |+| "Cats"
  println(result)

  val optionResult = Option(1) |+| Option(2)
  println(optionResult)

  val map1 = Map("a" -> 1, "b" -> 2)
  val map2 = Map("b" -> 3, "d" -> 4)
  val mapResult = map1 |+| map2
  println(mapResult)

  val tuple1 = ("hello", 123)
  val tuple2 = ("world", 321)
  val tupleResult = tuple1 |+| tuple2
  println(tupleResult)

}
