package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE02Tests extends AnyFlatSpec with should.Matchers:

  "Prime Numbers from from 1 to 10" should "be" in :
    import EE02.*
    val result = primeNumbers(10)
    result should be(List(2, 3, 5, 7))
