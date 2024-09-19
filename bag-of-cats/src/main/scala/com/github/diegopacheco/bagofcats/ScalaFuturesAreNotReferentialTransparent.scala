package com.github.diegopacheco.bagofcats

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.Random
import scala.concurrent.duration.DurationInt

implicit val ec: ExecutionContext = ExecutionContext.global

object ScalaFuturesAreNotReferentialTransparent extends App{

  val future1 = {
    // Initialize Random with a fixed seed:
    val r = new Random(0L)
    // nextInt has the side-effect of moving to
    // the next random number in the sequence:
    val x = Future(r.nextInt)
    for {
      a <- x
      b <- x
    } yield (a, b)
  }

  val future2 = {
    val r = new Random(0L)
    for {
      a <- Future(r.nextInt)
      b <- Future(r.nextInt)
    } yield (a, b)
  }

  val result1 = Await.result(future1, 1.second)
  println(result1)
  val result2 = Await.result(future2, 1.second)
  println(result2)

}
