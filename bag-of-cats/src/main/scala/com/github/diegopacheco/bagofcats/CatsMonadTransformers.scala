package com.github.diegopacheco.bagofcats

import cats.data.OptionT
import cats.instances.list._ // for Monad
import cats.syntax.applicative._ // for pure

type ListOption[A] = OptionT[List, A]

/**
 * Monad Transformers
 *  - Monad transformers are type constructors that wrap
 *    an existing monad and add additional capabilities.
 *  - They are useful when working with nested monads.
 * Use Cases are:
 *  - better code organization
 *  - avoid nested monads
 *  - avoid flatMap hell
 *  - avoid boilerplate code
 *
 * Cats provides transformers for many monads, each named with a T suffix:
 * EitherT composes Either with other monads, OptionT composes Option,
 * and so on.
 *
 */
object CatsMonadTransformers extends App {

  val result1: ListOption[Int] = OptionT(List(Option(10)))
  val result2: ListOption[Int] = 32.pure[ListOption]

  val res = result1.flatMap { (x: Int) =>
    result2.map { (y: Int) =>
      x + y
    }
  }
  println(res)

  import scala.concurrent.Future
  import cats.data.{EitherT, OptionT}
  import cats.instances.future._ // for Monad
  import scala.concurrent.Await
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._

  type FutureEither[A] = EitherT[Future, String, A]
  type FutureEitherOption[A] = OptionT[FutureEither, A]

  val futureEitherOr: FutureEitherOption[Int] =
    for {
      a <- 10.pure[FutureEitherOption]
      b <- 32.pure[FutureEitherOption]
    } yield a + b
  println(Await.result(futureEitherOr.value.value, 1.second))


  type ErrorOr[A] = Either[String, A]
  type ErrorOrOption[A] = OptionT[ErrorOr, A]

  val errorStack1 = OptionT[ErrorOr, Int](Right(Some(10)))
  val errorStack2 = 32.pure[ErrorOrOption]

  println(errorStack1.value)
  println(errorStack2.value.map(_.getOrElse(-1)))

  println(futureEitherOr)
  val intermediate = futureEitherOr.value
  println(intermediate)

  val stack = intermediate.value
  println(stack)
  println(Await.result(stack, 1.second))


}
