package com.github.diegopacheco.scalaplayground

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global

object NumberGenerator {
  def even: Future[List[Int]] = Future {
    LazyList.from(0, 2).takeWhile(_ => true).toList
  }
  def odd: Future[List[Int]] = Future {
    LazyList.from(1, 2).takeWhile(_ => true).toList
  }
}