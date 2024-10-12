package com.github.diegopacheco.bagofcats.exercises

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell
import cats.instances.vector._ // for Monoid

type Logged[A] = Writer[Vector[String], A]

object MonadsWriterV1 extends App{

  val res0 = 42.pure[Logged]
  println(res0)

  val res1 = Vector("Message").tell
  println(res1)

  val res2 = 41.pure[Logged].map(_ + 1)
  println(res2)

}
