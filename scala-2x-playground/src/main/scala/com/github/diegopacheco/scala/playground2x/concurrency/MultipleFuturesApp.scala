package com.github.diegopacheco.scala.playground2x.concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object StockOps {

  def getStockPrice(stockSymbol: String): Future[Double] = Future {
    val r = scala.util.Random
    val randomSleepTime = r.nextInt(3000)
    println(s"For $stockSymbol, sleep time is $randomSleepTime")
    val randomPrice = r.nextDouble * 1000
    sleep(randomSleepTime)
    randomPrice
  }

  def sleep(time: Long): Unit = Thread.sleep(time)
  def currentTime: Long = System.currentTimeMillis()
  def deltaTime(t0: Long): Long = currentTime - t0
}

object MultipleFuturesApp extends App{

  import StockOps._

  val startTime = currentTime
  val aaplFuture = getStockPrice("AAPL")
  val amznFuture = getStockPrice("AMZN")
  val googFuture = getStockPrice("GOOG")

  // get a combined result in a for-expression
  val result: Future[(Double, Double, Double)] = for {
    aapl <- aaplFuture
    amzn <- amznFuture
    goog <- googFuture
  } yield (aapl, amzn, goog)

  result.onComplete {
    case Success(x) => {
      val totalTime = deltaTime(startTime)
      println(s"In Success case, time delta: ${totalTime}")
      println(s"The stock prices are: $x")
    }
    case Failure(e) => e.printStackTrace()
  }

  Await.result(result, scala.concurrent.duration.Duration.Inf)

}
