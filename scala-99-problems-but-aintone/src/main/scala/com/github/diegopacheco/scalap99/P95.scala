package com.github.diegopacheco.scalap99

// P95 (**) English number words.
//
// On financial documents, like checks, numbers must sometimes be written in full
// words.Example: 175 must be written as one-seven-five.
// Write a function fullWords(num: Int)
// to print (non-negative) integer numbers in full words
//
object P95 {
  private val digitWords = Map(
    '0' -> "zero",
    '1' -> "one",
    '2' -> "two",
    '3' -> "three",
    '4' -> "four",
    '5' -> "five",
    '6' -> "six",
    '7' -> "seven",
    '8' -> "eight",
    '9' -> "nine"
  )
  def fullWords(num: Int): String = {
    num.toString.map(digitWords).mkString("-")
  }
}

object P95Main extends App {
  import P95._

  val number = 175
  println(fullWords(number))  // Output: one-seven-five
}