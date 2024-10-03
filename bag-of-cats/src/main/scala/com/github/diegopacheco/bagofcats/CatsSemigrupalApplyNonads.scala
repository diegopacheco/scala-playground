package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.syntax.functor.*
import cats.syntax.flatMap.*

import scala.concurrent.Future // for flatmap

object CatsSemigrupalApplyNonads extends App{

  def product[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A,B)] =
    for {
      a <- fa
      b <- fb
    } yield (a, b)

  val res = product(Option(1), Option(2))
  println(res)

  val a = Future("Future 1")
  val b = Future("Future 2")
  for {
    x <- a
    y <- b
  } yield (x, y)
  val res1 = product(a,b)
  println(res1)

}
