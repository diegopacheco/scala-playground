package com.github.diegopacheco.bagofcats

import cats.Foldable
import cats.instances.list._ // for Foldable
import cats.instances.option._ // for Foldable
import cats.Eval
import cats.Foldable
import cats.instances.lazyList._ // for Foldable

object CatsFoldable extends App {

  val ints = List(1, 2, 3)
  val res0 = Foldable[List].foldLeft(ints, 0)(_ + _) // 6
  println(res0)

  val maybeInt = Option(123)
  val res1 = Foldable[Option].foldLeft(maybeInt, 10)(_ * _) // 1230
  println(res1)

  def bigData = (1 to 100000).to(LazyList)

  val res2 = bigData.foldRight(0L)(_ + _)
  println(res2)

  val eval: Eval[Long] =
    Foldable[LazyList].
      foldRight(bigData, Eval.now(0L)) { (num, eval) =>
        eval.map(_ + num)
      }
  println(eval.value)

  val res3 = (1 to 100000).toList.foldRight(0L)(_ + _)
  println(res3)

  val res4 = (1 to 100000).toVector.foldRight(0L)(_ + _)
  println(res4)



}
