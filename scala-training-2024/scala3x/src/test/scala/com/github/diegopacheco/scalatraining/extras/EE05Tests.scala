package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE05Tests extends AnyFlatSpec with should.Matchers:

    "countOccurrences should return Map(1 -> 4, 2 -> 3, 3 -> 2, 4 -> 1)" should "be" in:
      import EE05.*
      val list = List(1, 2, 3, 4, 1, 2, 3, 1, 2, 1)
      val result = countOccurrences(list)
      result should be(Map(1 -> 4, 2 -> 3, 3 -> 2, 4 -> 1))
