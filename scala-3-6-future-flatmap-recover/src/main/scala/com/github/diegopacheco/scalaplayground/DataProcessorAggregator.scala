package com.github.diegopacheco.scalaplayground

import scala.concurrent.Future

object DataProcessorAggregator {

  private val processor = DataProcessor

  def process(ids: List[Int]): Future[List[Int]] = {
    val futureResults = for {
      result <- processor.process(ids).flatMap {
        case Left(value) => Future.successful(value)
        case Right(_) => Future.successful(List.empty[Int])
      }.recoverWith {
        case e: Any =>
          println("[Recover] Got this: " + e)
          Future.successful(List.empty[Int])
      }
    } yield result
    futureResults
  }

  def processException(ids: List[Int]): Future[List[Int]] = {
    val futureResults = for {
      result <- processor.processException(ids).
        flatMap {
          case Left(value) => Future.successful(value)
          case Right(_) => Future.successful(List.empty[Int])
        }.recoverWith {
          case e: Any =>
            println("[Recover] Got this: " + e)
            Future.successful(List.empty[Int])
        }
    } yield result
    futureResults
  }

}
