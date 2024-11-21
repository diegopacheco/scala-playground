package com.github.diegopacheco.scala3x.playground.meh

//
// https://docs.scala-lang.org/scala3/reference/new-types/polymorphic-function-types.html
//
// Polymorphic Function Types
//
// A polymorphic method is sufficient for many use cases, but polymorphic
// function values provide additional flexibility and expressiveness.
//
object PolymorphicFunctionTypes extends App:

  // A polymorphic method:
  def rev[A](xs: List[A]): List[A] = xs.reverse

  // A polymorphic function value:
  val bar: [A] => List[A] => List[A]
  //       ^^^^^^^^^^^^^^^^^^^^^^^^^
  //       a polymorphic function type
  = [A] => (xs: List[A]) => rev[A](xs)

  val result = bar(List(1, 2, 3))
  println(result)

  val result2 = bar(List("a", "b", "c"))
  println(result2)