package com.github.diegopacheco.bagofcats

import cats.Functor
import cats.instances.list._   // for Functor
import cats.instances.option._ // for Functor

object FunctorTypeClassInstances extends App{

  val list1 = List(1, 2, 3)
  println(list1)

  val list2 = Functor[List].map(list1)(_ * 2)
  println(list2)

  val option1 = Option(123)
  println(option1)

  val option2 = Functor[Option].map(option1)(_.toString)
  println(option2)

  /**
   * Lift => Functor lift method
   *   convert a function A => B to a function F[A] => F[B]
   */
  val func = (x: Int) => x + 1
  val liftedFunc = Functor[Option].lift(func)
  println(liftedFunc(Option(1)))
  println(Functor[List].as(list1, "As"))

}
