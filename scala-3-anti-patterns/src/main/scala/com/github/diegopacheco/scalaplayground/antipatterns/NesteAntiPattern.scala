package com.github.diegopacheco.scalaplayground.antipatterns

object NesteAntiPattern extends App{
  val condition1 = true
  val condition2 = false
  val condition3 = true
  val result = if (condition1) {
    if (condition2) {
      if (condition3) {
        "Deeply nested" // Anti-pattern
      } else {
        "Still nested"
      }
    } else {
      "Nested"
    }
  } else {
    "Not nested"
  }
  println(result)
}
