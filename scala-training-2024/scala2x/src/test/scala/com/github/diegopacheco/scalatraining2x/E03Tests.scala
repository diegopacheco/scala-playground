package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._

class E03Tests extends AnyFlatSpec with should.Matchers {
    "10*10" should "be" in {
      val result = E03.run("10*10")
      result should not be None
      result.get should be(100)
    }
}

