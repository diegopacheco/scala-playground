package com.github.diegopacheco.scalaplayground

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.Future

class NumberGeneratorTest extends AsyncFlatSpec with Matchers {

  "NumberGenerator" should "generate an infinite list of even numbers" in {
    NumberGenerator.even.map { evens =>
      evens.take(10) shouldEqual List(0, 2, 4, 6, 8, 10, 12, 14, 16, 18)
    }
  }

  it should "generate an infinite list of odd numbers" in {
    NumberGenerator.odd.map { odds =>
      odds.take(10) shouldEqual List(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)
    }
  }

  it should "combine even and odd numbers into a sorted list" in {
    NumberGenerator.combined.map { combined =>
      combined.take(20) shouldEqual List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
    }
  }
}