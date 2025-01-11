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

object MainApp extends App{
  proofOne()
}

