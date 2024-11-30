package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._

class E02Tests extends AnyFlatSpec with should.Matchers {

  "Benchmark" should "be" in {
    val lazyBench = E02.benchmarkMS {
      Thread.sleep(1000)
    }
    lazyBench() should not be None
    lazyBench() should be >= 1000L
  }


}

