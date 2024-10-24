package com.github.diegopacheco.scalapatterns.traversable


import com.github.diegopacheco.scalapatterns.applicative.Applicative

trait Applicative[F[_]] {
  def pure[A](a: A): F[A]
  def map[A, B](fa: F[A])(f: A => B): F[B]
  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
}

object OptionApplicative extends Applicative[Option] {
  def pure[A](a: A): Option[A] = Some(a)

  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
    case Some(a) => Some(f(a))
    case None => None
  }

  def map2[A, B, C](fa: Option[A], fb: Option[B])(f: (A, B) => C): Option[C] = (fa, fb) match {
    case (Some(a), Some(b)) => Some(f(a, b))
    case _ => None
  }
}

trait Traversable[F[_]] {
  def traverse[G[_], A, B](fa: F[A])(f: A => G[B])(using G: Applicative[G]): G[F[B]]
}

object ListTraversable extends Traversable[List] {
  def traverse[G[_], A, B](fa: List[A])(f: A => G[B])(using G: Applicative[G]): G[List[B]] = {
    fa.foldRight(G.pure(List.empty[B])) { (a, glb) =>
      G.map2(f(a), glb)(_ :: _)
    }
  }
}

object TraversableApp extends App {
  val list = List(1, 2, 3)
  val result = ListTraversable.traverse(list)(x => Some(x + 1))(using OptionApplicative)

  println(result) // Output: Some(List(2, 3, 4))
}