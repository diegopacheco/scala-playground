package com.github.diegopacheco.scalaplayground

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global

object NumberGenerator {

  def even: Future[List[Int]] = Future {
    LazyList.from(0, 2).take(100).toList
  }

  def odd: Future[List[Int]] = Future {
    LazyList.from(1, 2).take(100).toList
  }

  def combined: Future[List[Int]] = {
    for {
      evens <- even
      odds <- odd
    } yield (evens ++ odds).sorted
  }
}