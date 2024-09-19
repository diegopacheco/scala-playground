package com.github.diegopacheco.bagofcats

import cats.Monoid
import cats.instances.string.*
import cats.syntax.semigroup.*
import cats.instances.int.*
import cats.instances.option.*
import cats.instances.map.*
import cats.instances.tuple.*
import cats.syntax.monoid

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

  def addAll[A](values: List[A])(implicit monoid: Monoid[A]): A =
    values.foldRight(monoid.empty)(_ |+| _)

  println(addAll(List(1, 2, 3)))
  println(addAll(List(None, Some(1), Some(2))))
  println(addAll(List(Option(1), Option(2), Option(3))))
  println(addAll(List("A", "B", "C")))

  // same as addAll but less exoteric
  // explicit, does not use implicits
  // you know upfront what you are doing
  // no exoteric operators |+|
  def addAll2[A: Monoid](values: List[A]): A =
    values.foldRight(Monoid[A].empty)(_.combine(_))

  println(addAll2(List(1, 2, 3)))
  println(addAll2(List(None, Some(1), Some(2))))
  println(addAll2(List(Option(1), Option(2), Option(3))))
  println(addAll2(List("A", "B", "C")))

}
