package com.github.diegopacheco.bagofcats

import cats.Eval

/**
 *
 * Eval Monad
 *  is a Monad that allows you to control the evaluation of values.
 *
 * Example Use cases are:
 * - Lazy evaluation
 * - Memoization
 * - Trampolining
 *
 * Scala                       Cats            Properties
 * val                         Now             eager, memoized
 * def                         Always          lazy, memoized
 * lazy val                    Later           lazy, not memoized
 */
object CatsMonadEval extends App{

  val now = Eval.now(math.random + 1000)
  println(now)
  println(now)
  println(now)

  val always = Eval.always(math.random + 3000)
  println(always.value)
  println(always.value)
  println(always.value)

  val later = Eval.later(math.random + 2000)
  println(later.value)
  println(later.value)
  println(later.value)

  val greeting = Eval
    .always {
      println("Step 1"); "Hello"
    }
    .map { str => println("Step 2"); s"$str world" }
  println(greeting.value)

  val ans = for {
    a <- Eval.now {
      println("Calculating A"); 40
    }
    b <- Eval.always {
      println("Calculating B"); 2
    }
  } yield {
    println("Adding A and B")
    a + b
  }
  println(ans.value)

  val saying = Eval
    .always {
      println("Step 1"); "The cat"
    }
    .map { str => println("Step 2"); s"$str sat on" }
    .memoize
    .map { str => println("Step 3"); s"$str the mat" }
  println(saying.value)

  // Trampolining
  // Eval.defer (Stack safe)
  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }

  println(factorial(50000).value)

}
