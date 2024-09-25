package com.github.diegopacheco.bagofcats

import cats.data.Writer
import cats.instances.vector._ // for Monoid
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell / writer

/**
 * Writer Monad is a Monad that allows you to carry a log along with a computation.
 * Examples:
 *  - Logging
 *  - Auditing
 *  - Undo/Redo
 *  - Stateful Computations
 *  - Additional Metadata
 *
 *  It's like a Wrapper Object.
 */
object CatsMonadWriter extends App {

  val res = Writer(Vector(
    "It was the best of times",
    "it was the worst of times"
  ), 1859)
  println(res)

  type Logged[A] = Writer[Vector[String], A]
  val res1 = 123.pure[Logged]
  println(res1)

  val res2 = Vector("msg1", "msg2", "msg3").tell
  println(res2)

  val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
  println(a)

  val b = 123.writer(Vector("msg1", "msg2", "msg3"))
  println(b)

  val aResult: Int = a.value
  val aLog: Vector[String] = a.written
  println(s"Result: $aResult - Log: $aLog")

  val (log, result) = b.run
  println(s"Result: $result - Log: $log")

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b
  val res3 = writer1.run
  println(res3)

  val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
  val res4 = writer2.run
  println(res4)

  val writer3 = writer1.bimap(
    log => log.map(_.toUpperCase),
    res => res * 100
  )
  val res5 = writer3.run
  println(res5)

}
