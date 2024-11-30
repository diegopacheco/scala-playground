package com.github.diegopacheco.scalatraining

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E03Tests extends AnyFlatSpec with should.Matchers:

  "RPN calculation of 3 4 + 2 * 1 +" should "be 15" in:
    import E03.*
    val result = rpnCalculator("3 4 + 2 * 1 +")
    result should not be(None)
    result should be(15)
