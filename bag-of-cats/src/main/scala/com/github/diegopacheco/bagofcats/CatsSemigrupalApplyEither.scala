package com.github.diegopacheco.bagofcats

import cats.Semigroupal
import cats.instances.either.* // for Semigroupal

object CatsSemigrupalApplyEither extends App{

  type ErrorOr[A] = Either[Vector[String], A]

  val res = Semigroupal[ErrorOr].product(
    Left(Vector("Error 1")),
    Left(Vector("Error 2"))
  )
  println(res)


}
