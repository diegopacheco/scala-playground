package com.github.diegopacheco.bagofcats

import cats.Semigroupal
import cats.instances.future._ // for Semigroupal
import scala.concurrent._
import scala.concurrent.duration._
import cats.syntax.apply._ // for mapN

object CatsSemigrupalApplyFuture extends App {

  implicit val ec:ExecutionContext = ExecutionContext.global

  val futurePair = Semigroupal[Future].
    product(Future("Hello"), Future(123))

  val res = Await.result(futurePair, 1.second)
  println(res)

  case class Cat(
    name: String,
    yearOfBirth: Int,
    favoriteFoods: List[String]
  )

  val futureCat = (
    Future("Garfield"),
    Future(1978),
    Future(List("Lasagne"))
  ).mapN(Cat.apply)
  
  val futureCatRes = Await.result(futureCat, 1.second)
  println(futureCatRes)

}
