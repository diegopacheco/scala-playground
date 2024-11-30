package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE03Tests extends AnyFlatSpec with should.Matchers:
  "QuickSort should sort List(5, 3, 1, 2, 4)" should "be List(1, 2, 3, 4, 5) as " in:
    import EE03.*
    val result = quicksort(List(5, 3, 1, 2, 4))
    result should be(List(1, 2, 3, 4, 5))
