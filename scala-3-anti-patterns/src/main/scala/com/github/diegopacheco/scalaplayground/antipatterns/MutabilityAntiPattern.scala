package com.github.diegopacheco.scalaplayground.antipatterns

import scala.collection.mutable.ArrayBuffer

//
// Anti-Pattern (Unnecessary Mutability)
// 1. Using mutable variables (var)
// 2. Using mutable collections
// 3. Using global variables in functions
//
def getLuckyNumbers:ArrayBuffer[Int] = {
  var numbers = ArrayBuffer[Int]() //Anti-pattern 1 and 2
  numbers += 1
  numbers += 2
  numbers += 3
  for (i <- numbers.indices) {
    numbers(i) = numbers(i) * 2
  }
  numbers
}

object MutabilityAntiPattern extends App{
  private var luckyNumbers: ArrayBuffer[Int] = getLuckyNumbers
  println(luckyNumbers)

  private var total = 0 // Anti-pattern 3
  private val add = (x: Int) => total += x
  println(add(5))
}
