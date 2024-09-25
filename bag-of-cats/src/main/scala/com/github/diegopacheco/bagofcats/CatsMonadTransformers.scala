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

}
