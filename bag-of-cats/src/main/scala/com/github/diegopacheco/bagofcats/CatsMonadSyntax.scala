package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.syntax.applicative._ // for pure
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
import cats.instances.option._ // for Monad
import cats.instances.list._ // for Monad

object CatsMonadSyntax extends App{

  val mResult1 = 1.pure[Option]
  println(mResult1)

  val mResult2 = 1.pure[List]
  println(mResult2)

  def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x * x + y * y))

  val res3 = sumSquare(Option(3), Option(4))
  println(res3)

  val res4 = sumSquare(List(1, 2, 3), List(4, 5))
  println(res4)

  def sumSquare2[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x*x + y*y

  val res5 = sumSquare2(Option(3), Option(4))
  println(res5)

  val res6 = sumSquare(List(1, 2, 3), List(4, 5))
  println(res6)

}
