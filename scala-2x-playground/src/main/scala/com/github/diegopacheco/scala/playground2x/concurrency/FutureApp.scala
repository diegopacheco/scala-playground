package com.github.diegopacheco.scala.playground2x.concurrency

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object FutureApp extends App{

  val futureVal = Future { Thread.sleep(2*100); 42 }
  val doubleFuture = futureVal.map(_ * 2)
  doubleFuture.onComplete {
    case Success(value) => println(s"Got the callback, value = $value")
    case Failure(e) => e.printStackTrace()
  }

  val allFutures = Future.sequence(List(futureVal, doubleFuture))
  Await.result(allFutures, 1.second)
}
