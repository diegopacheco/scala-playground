package com.github.diegopacheco.scalapatterns.functor

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object OptionFunctor extends Functor[Option] {
  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
    case Some(a) => Some(f(a))
    case None => None
  }
}

object FunctorApp extends App{
  def addOne(x: Int): Int = x + 1

  val optionValue: Option[Int] = Some(3)
  val result: Option[Int] = OptionFunctor.map(optionValue)(addOne)

  println(result) // Output: Some(4)
}
