package com.github.diegopacheco.bagofcats

import cats.Semigroupal
import cats.instances.option._ // for Semigroupal

/**
 * Semigroupal
 *   is a type class that abstracts the ability to combine contexts.
 * Use Cases are:
 *  - Combining multiple independent contexts
 *  - Form validation
 *  - Parallel programming
 *  - Error handling
 *
 */
object CatsSemigroupal extends App{

  val res = Semigroupal[Option].product(Some(123), Some("abc"))
  println(res)

  val res2 = Semigroupal[Option].product(None, Some("abc"))
  println(res2)

  val res3 = Semigroupal[Option].product(Some(123), None)
  println(res3)

  val res4 = Semigroupal.tuple3(Option(1), Option(2), Option(3))
  println(res4)

  val res5 = Semigroupal.tuple3(Option(1), Option(2), Option.empty[Int])
  println(res5)

  val res6 = Semigroupal.map3(Option(1), Option(2), Option(3))(_ + _ + _)
  println(res6)

  val res7 = Semigroupal.map2(Option(1), Option.empty[Int])(_ + _)
  println(res7)

}
