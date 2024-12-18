package com.github.diegopacheco.scalaplayground.antipatterns

import scala.collection.mutable.ArrayBuffer

def getLuckyNumbers:ArrayBuffer[Int] = {
  var numbers = ArrayBuffer[Int]()
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

  var total = 0 // Anti-pattern
  val add = (x: Int) => total += x
  println(add(5))
}
