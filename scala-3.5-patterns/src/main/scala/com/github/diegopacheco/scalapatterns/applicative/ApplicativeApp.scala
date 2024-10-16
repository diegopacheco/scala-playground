package com.github.diegopacheco.scalapatterns.applicative

import com.github.diegopacheco.scalapatterns.functor.Functor

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
}

object OptionApplicative extends Applicative[Option] {
  def pure[A](a: A): Option[A] = Some(a)

  def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] = (ff, fa) match {
    case (Some(f), Some(a)) => Some(f(a))
    case _ => None
  }

  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
    case Some(a) => Some(f(a))
    case None => None
  }
}

object ApplicativeApp extends App{

  val optionFunc: Option[Int => Int] = Some((x: Int) => x + 1)
  val optionValue: Option[Int] = Some(3)
  val result: Option[Int] = OptionApplicative.ap(optionFunc)(optionValue)

  println(result) // Output: Some(4)

}
