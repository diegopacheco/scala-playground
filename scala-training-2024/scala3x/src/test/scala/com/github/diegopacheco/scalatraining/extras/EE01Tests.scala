package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE01Tests extends AnyFlatSpec with should.Matchers:

    "Fibonacci from 1 to 10" should "be" in:
      import EE01.*
      val result = fibonacci(10)
      result should be(List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55))
