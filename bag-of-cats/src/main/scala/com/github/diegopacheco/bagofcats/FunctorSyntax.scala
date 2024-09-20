package com.github.diegopacheco.bagofcats

import cats.Functor
import cats.instances.function.*
import cats.syntax.functor.*  // for map
import cats.instances.option._ // for Functor
import cats.instances.list._  // for Functor

object FunctorSyntax extends App{

  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => s"${a}!"
  val func4 = func1.map(func2).map(func3)
  println(func4(123))

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => (n + 1) * 2)

  println(doMath(Option(20)))
  println(doMath(List(1, 2, 3)))

  /**
   * Same but without implicits
  */
  def doMath2[F[_]: Functor](start: F[Int]): F[Int] =
    Functor[F].map(start)(n => (n + 1) * 2)

  println(doMath2(Option(20)))
  println(doMath2(List(1, 2, 3)))

}
