package com.github.diegopacheco.scalaplayground

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global

object NumberGenerator {

  def even(upTo:Int): Future[List[Int]] = Future {
    LazyList.from(0, 2).take(upTo).toList
  }

  def odd(upTo:Int): Future[List[Int]] = Future {
    LazyList.from(1, 2).take(upTo).toList
  }

  def combined(upTo:Int): Future[List[Int]] = {
    for {
      evens <- even(upTo)
      odds <- odd(upTo)
    } yield (evens ++ odds).sorted
  }
}