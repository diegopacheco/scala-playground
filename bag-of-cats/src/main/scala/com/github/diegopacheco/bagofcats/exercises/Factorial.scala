package com.github.diegopacheco.bagofcats.exercises

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell

type LoggedFac[A] = Writer[Vector[String], A]

object Factorial extends App{

  def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)

  def factorial(n: Int): LoggedFac[Int] =
    for {
      ans <- if (n == 0) {
        1.pure[LoggedFac]
      } else {
        slowly(factorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans

  val (log, res) = factorial(5).run
  println(s"Log: ${log.mkString("\n")}")

}
