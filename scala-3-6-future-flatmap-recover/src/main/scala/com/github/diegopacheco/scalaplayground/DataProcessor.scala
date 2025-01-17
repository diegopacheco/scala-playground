package com.github.diegopacheco.scalaplayground

import scala.concurrent.Future
implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

object DataProcessor {

  def process(ids: List[Int]): Future[Either[List[Int], String]] = {
    val future: Future[List[Int]] = Future {
      ids.map(id => id + 1)
    }
    future.map(Left(_)).recover {
      case e: Exception => Right("Oopsy Dazes: Error")
    }
  }

  def processException(ids: List[Int]): Future[Either[List[Int], String]] = {
    val future: Future[List[Int]] = Future {
      ids.map(id => {
        if (id == 2) {
          throw new RuntimeException("Error 2 is bad")
        } else {
          id + 1
        }
      })
    }
    future.map(Left(_)).recover {
      case e: Exception => throw new RuntimeException("Error 2 is bad")
    }
  }

}
