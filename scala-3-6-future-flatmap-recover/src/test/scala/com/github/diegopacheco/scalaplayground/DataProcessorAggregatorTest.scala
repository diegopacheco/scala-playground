package com.github.diegopacheco.scalaplayground

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.Future

class DataProcessorAggregatorTest extends AsyncFlatSpec with Matchers {

  "DataProcessorAggregator" should "process ids correctly" in {
    val ids = List(1, 2, 3)
    val result: Future[List[Int]] = DataProcessorAggregator.process(ids)
    result.map { res =>
      res should contain allOf (2, 3, 4)
    }
  }

  it should "handle exceptions and recover with an empty list" in {
    val ids = List(2)
    val result: Future[List[Int]] = DataProcessorAggregator.processException(ids)
    result.map { res =>
      res shouldBe empty
    }
  }
}