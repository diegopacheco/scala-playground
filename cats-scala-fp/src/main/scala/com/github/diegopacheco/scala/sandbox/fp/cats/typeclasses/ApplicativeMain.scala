package com.github.diegopacheco.scala.sandbox.fp.cats.typeclasses

/**
 *
 * Applicative
 * Applicative extends Functor with an ap and pure method.
 *
 * import cats.Functor
 *
 *  trait Applicative[F[_]] extends Functor[F] {
 *   def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
 *   def pure[A](a: A): F[A]
 *   def map[A, B](fa: F[A])(f: A => B): F[B] = ap(pure(f))(fa)
 * }
 *
 * pure wraps the value into the type constructor - for Option this could be Some(_), for Future Future.successful, and for List a singleton list.
 * ap is a bit tricky to explain and motivate.
 *
 * Such an Applicative must obey three laws:
 *
 * 1 - Associativity: No matter the order in which you product together three values, the result is isomorphic
 * fa.product(fb).product(fc) ~ fa.product(fb.product(fc))
 * With map, this can be made into an equality with fa.product(fb).product(fc) = fa.product(fb.product(fc)).map { case (a, (b, c)) => ((a, b), c) }
 *
 * 2 - Left identity: Zipping a value on the left with unit results in something isomorphic to the original value
 * pure(()).product(fa) ~ fa
 * As an equality: pure(()).product(fa).map(_._2) = fa
 *
 * 3 - Right identity: Zipping a value on the right with unit results in something isomorphic to the original value
 * fa.product(pure(())) ~ fa
 * As an equality: fa.product(pure(())).map(_._1) = fa
 *
 */
object ApplicativeMain extends App {

  import cats.Applicative
  import cats.data.Nested
  import cats.instances.future._
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import cats.instances.option._
  import cats.implicits._
  import cats.instances.list._
  import cats.syntax.traverse._
  
  val f: (Int, Char) => Double = (i, c) => (i + c).toDouble
  val int: Option[Int] = Some(5)
  val char: Option[Char] = Some('a')
  println(int.map(i => (c: Char) => f(i, c)))

}