package com.github.diegopacheco.bagofcats

import cats.data.State
import State._

/**
 * State Monad
 *  is a monad that allows us to carry some state along with our computation.
 *
 * Use cases are:
 * - Random number generation
 * - Simulation
 * - Parsing
 * - Compilers
 * - Interpreters
 *
 */
object CatsStateMonad extends App{

  val a = State[Int, String] { state =>
    (state, s"The state is $state")
  }
  println(a)

  val (state, result) = a.run(10).value
  println(s"State: $state - Result: $result")

  val justTheState = a.runS(10).value
  println(s"Just the state: $justTheState")

  val justTheResult = a.runA(10).value
  println(s"Just the result: $justTheResult")

  val step1 = State[Int, String] { num =>
    val ans = num + 1
    (ans, s"Result of step1: $ans")
  }

  val step2 = State[Int, String] { num =>
    val ans = num * 2
    (ans, s"Result of step2: $ans")
  }

  val both = for {
    a <- step1
    b <- step2
  } yield (a, b)

  val (state2, result2) = both.run(20).value
  println(s"State: $state2 - Result: $result2")

  val program: State[Int, (Int, Int, Int)] = for {
    a <- get[Int]
    _ <- set[Int](a + 1)
    b <- get[Int]
    _ <- modify[Int](_ + 1)
    c <- inspect[Int, Int](_ * 1000)
  } yield (a, b, c)
  val (state3, result3) = program.run(1).value
  println(s"State: $state3 - Result: $result3")

}
