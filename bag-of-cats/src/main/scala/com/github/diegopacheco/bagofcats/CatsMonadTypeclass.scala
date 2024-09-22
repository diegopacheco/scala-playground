package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad

/**
 * Monads are a design pattern that allows you to chain operations together.
 * Moands need to have 2 operations: flatMap and unit.
 */
object CatsMonadTypeclass extends App{

  val opt1 = Monad[Option].pure(3)
  println(opt1)

  val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
  println(opt2)

  val opt3 = Monad[Option].map(opt2)(a => 100 * a)
  println(opt3)

  val list1 = Monad[List].pure(3)
  println(list1)

  val list2 = Monad[List].
    flatMap(List(1, 2, 3))(a => List(a, a * 10))
    println(list2)

  val list3 = Monad[List].map(list2)(a => a + 123)
  println(list3)
}
