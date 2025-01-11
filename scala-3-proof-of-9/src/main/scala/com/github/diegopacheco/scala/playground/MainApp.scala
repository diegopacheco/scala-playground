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

object MainApp extends App{
  proofOne()
  proofTwo()
  proofThree()
}

