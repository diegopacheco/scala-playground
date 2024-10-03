package com.github.diegopacheco.bagofcats

import cats.Semigroupal
import cats.instances.either._ // for Semigroupal
import cats.syntax.apply._ // for tupled
import cats.instances.vector._ // for Semigroup on Vector
import cats.syntax.parallel._ // for parTupled
import cats.instances.list._ // for Semigroup on List

object CatsParallel extends App{

  type ErrorOr[A] = Either[Vector[String], A]
  val error1: ErrorOr[Int] = Left(Vector("Error 1"))
  val error2: ErrorOr[Int] = Left(Vector("Error 2"))

  val res = Semigroupal[ErrorOr].product(error1, error2)
  println(res)

  val res1 = (error1, error2).tupled
  println(res1)

  val res2 = (error1, error2).parTupled
  println(res2)

  type ErrorOrList[A] = Either[List[String], A]
  val errStr1: ErrorOrList[Int] = Left(List("error 1"))
  val errStr2: ErrorOrList[Int] = Left(List("error 2"))
  println((errStr1, errStr2).parTupled)

  val success1: ErrorOr[Int] = Right(1)
  val success2: ErrorOr[Int] = Right(2)
  val addTwo = (x: Int, y: Int) => x + y
  val res3 = (error1, error2).parMapN(addTwo)
  println(res3)

  import cats.arrow.FunctionK
  object optionToList extends FunctionK[Option, List] {
    def apply[A](fa: Option[A]): List[A] =
      fa match {
        case None => List.empty[A]
        case Some(a) => List(a)
      }
  }
  val res4 = optionToList(Some(1))
  println(res4)

  val res5 = optionToList(None)
  println(res5)

}
