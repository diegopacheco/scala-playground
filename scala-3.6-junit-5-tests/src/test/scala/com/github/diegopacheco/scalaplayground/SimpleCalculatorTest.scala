package com.github.diegopacheco.scalaplayground

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class SimpleCalculatorTest {

  @Test
  def testAdd(): Unit = {
    assertEquals(5, SimpleCalculator.add(2, 3))
    println(s"Test add 2+3=${SimpleCalculator.add(2, 3)}")
  }

  @Test
  def testSub(): Unit = {
    assertEquals(1, SimpleCalculator.sub(3, 2))
    println(s"Test sub 3-2=${SimpleCalculator.sub(3, 2)}")
  }

  @Test
  def testMul(): Unit = {
    assertEquals(6, SimpleCalculator.mul(2, 3))
    println(s"Test nul 2*3=${SimpleCalculator.mul(2, 3)}")
  }

  @Test
  def testDiv(): Unit = {
    assertEquals(2, SimpleCalculator.div(6, 3))
    println(s"Test div 6/3=${SimpleCalculator.div(6, 3)}")
  }

  @Test
  def testMod(): Unit = {
    assertEquals(1, SimpleCalculator.mod(7, 3))
    println(s"Test mod 7%3=${SimpleCalculator.mod(7, 3)}")
  }

  @Test
  def testPow(): Unit = {
    assertEquals(8, SimpleCalculator.pow(2, 3))
    println(s"Test pow 2^3=${SimpleCalculator.pow(2, 3)}")
  }
}