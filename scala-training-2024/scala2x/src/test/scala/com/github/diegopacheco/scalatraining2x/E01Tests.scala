package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._
import E01._

class E01Tests extends AnyFlatSpec with should.Matchers {

  "Last element of this list " should "be" in {
    aResult should not be None
    aResult should be (10)
  }

  it should "have the right even numbers on the list" in {
    bResult should not be None
    bResult should be (List(2,4,6,8,10))
  }

  "IF the number of Odd multiply by 3, and them if is even sum 2" should "be" in {
    cResult should not be None
    cResult should be (List(4, 8, 12, 16, 20))
  }

  "Sum all numbers that are bigger than 4 and double, each one of them, print only if number is even" should "be" in {
    dResult should not be None
    dResult should be (90)
  }

}