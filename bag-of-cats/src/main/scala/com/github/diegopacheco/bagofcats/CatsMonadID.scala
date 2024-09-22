package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.Id
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap

object CatsMonadID extends App{

  def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x * x + y * y

   println(sumSquare(3 : Id[Int], 4 : Id[Int]))

   val res = "Dave" : Id[String]
   println(res)

  val res1 = 123: Id[Int]
  println(res1)

  val res2 = List(1, 2, 3) : Id[List[Int]]
  println(res2)

  val a = Monad[Id].pure(3)
  println(a)

  val b = Monad[Id].flatMap(a)(_ + 1)
  println(b)

  val idRes = for {
    x <- a
    y <- b
  } yield x + y
  println(idRes)

}
