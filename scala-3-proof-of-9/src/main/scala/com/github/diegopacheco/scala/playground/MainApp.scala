package com.github.diegopacheco.scala.playground

def proofOne(): Unit = {
  //
  // This function check the result before returning so the check in part of the function.
  //
  def isDivisibleBy9(number: Int): Boolean = {
    val sumOfDigits = number.toString.map(_.asDigit).sum
    sumOfDigits % 9 == 0
  }

  // Test the function
  val testNumber = 123456
  println(s"Is $testNumber divisible by 9? ${isDivisibleBy9(testNumber)}")
}

def proofTwo(): Unit = {
  //
  // This function also have an embedded check - the IF
  //
  def printOdd(n: Int): Unit = {
    if (n % 2 != 0) println(s"$n is odd")
  }
  (1 to 2).foreach(printOdd)
}

def proofThree(): Unit = {
  //
  // This function also have an embedded check - the assert (Exception is throw if fail)
  //
  def printOdd(n: Int): Unit = {
    assert(n % 2 == 0)
    println(s"$n is even")
  }
  (2 to 2).foreach(printOdd)
}

def proofFour(): Unit = {
  //
  // This is the classic proof of 9 - instead of doing number / 9 we have different math proof same result.
  // This is interesting concept because it checks the result in a different way.
  //
  def sumOfDigits(number: Int): Int = {
    number.toString.map(_.asDigit).sum
  }
  def isDivisibleBy9(number: Int): Boolean = {
    var sum = sumOfDigits(number)
    while (sum >= 9) {
      if (sum == 9) return true
      sum = sumOfDigits(sum)
    }
    false
  }

  // Test the function with multiple numbers
  val testNumbers = List(333333, 123456789)
  testNumbers.foreach { n =>
    println(s"Is $n divisible by 9? ${isDivisibleBy9(n)}")
  }
}

def proofFive(): Unit = {

  def getIDFromSystemOne(): Int = 22_500
  def getIDFromSystemTwo(): Int = 22_500
  def getIDFromSystemThree(): Int = 22_500

  //
  // BAD:
  //   1. only check IF ONE is present
  //   2. Does not CROSS_CHECK the IDS
  //
  val ids = List(getIDFromSystemOne(), getIDFromSystemTwo(), getIDFromSystemThree())
  if (null != ids && ids.nonEmpty) {
    println(s"BAD - I think ids are good because there is some ID - IDs: $ids")
  }

  //
  // BAD (but a bit better) - it check if all CROSS_CHECK matches but what means to have: 22_500 ???
  // There is an anti-pattern called Magical Numbers.
  //
  if (getIDFromSystemOne() == getIDFromSystemTwo() &&  getIDFromSystemOne() == getIDFromSystemThree()) {
    println(s"BAD - I think ids are good because IDS matches - IDs: $ids")
  }

}

object MainApp extends App{
  proofOne()
  proofTwo()
  proofThree()
  proofFour()
  proofFive()
}

