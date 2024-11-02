package com.github.diegopacheco.scalapatterns.coyoneda

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

case class Coyoneda[F[_], A, B](fa: F[A], k: A => B) {
  def map[C](f: B => C): Coyoneda[F, A, C] = Coyoneda(fa, k.andThen(f))

  def run(implicit F: Functor[F]): F[B] = F.map(fa)(k)
}

object Coyoneda {
  def lift[F[_], A](fa: F[A]): Coyoneda[F, A, A] = Coyoneda(fa, identity)
}

object OptionFunctor extends Functor[Option] {
  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
    case Some(a) => Some(f(a))
    case None => None
  }
}

object CoyonedaApp extends App{
  val option: Option[Int] = Some(1)

  val lifted = Coyoneda.lift(option)
  val transformed = lifted.map(_ + 1).map(_ * 2)

  val result = transformed.run(OptionFunctor)
  println(result) // Output: Some(4)
}
