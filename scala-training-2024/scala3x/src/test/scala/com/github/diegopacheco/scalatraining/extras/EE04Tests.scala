package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE04Tests extends AnyFlatSpec with should.Matchers:

  "BinarySearch should find 10 in Array(2, 3, 4, 10, 40)" should "be 3" in:
    import EE04.*
    val arr = Array(2, 3, 4, 10, 40)
    val x = 10
    val result = binarySearch(arr, x)
    result should be(3)

  "BinarySearch should not find 100 in Array(2, 3, 4, 10, 40)" should "be -1" in:
    import EE04.*
    val arr = Array(2, 3, 4, 10, 40)
    val x = 100
    val result = binarySearch(arr, x)
    result should be(-1)