package com.github.diegopacheco.scala.sandbox.fp.cats

/**
 * Monad
 * Monad extends the Applicative type class with a new function flatten. Flatten takes a value in a nested context (eg. F[F[A]] where F is the context)
 *  and “joins” the contexts together so that we have a single context (ie. F[A]).
 * The name flatten should remind you of the functions of the same name on many classes in the standard library.
 *
 *
 *
 */
object MonadMain extends App {

  println(Option(Option(1)).flatten)
  println(Option(None).flatten)
  println(List(List(1), List(2, 3)).flatten)

  import cats.implicits._
  import cats.Monad

  val result = Monad[List].ifM(List(true, false, true))(ifTrue = List(1, 2), ifFalse = List(3, 4))
  println(result)

  import scala.annotation.tailrec

  implicit val optionMonad = new Monad[Option] {
    def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)
    def pure[A](a: A): Option[A] = Some(a)

    @tailrec
    def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] = f(a) match {
      case None              => None
      case Some(Left(nextA)) => tailRecM(nextA)(f) // continue the recursion
      case Some(Right(b))    => Some(b) // recursion done
    }
  }
  println(optionMonad)
  
  
  import scala.reflect.runtime.universe
  val re = universe.reify(
    for {
      x <- Some(1)
      y <- Some(2)
    } yield x + y
  ).tree
  println(re)
  
}