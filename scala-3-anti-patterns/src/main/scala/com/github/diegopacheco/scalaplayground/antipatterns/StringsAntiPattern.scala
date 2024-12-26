package com.github.diegopacheco.scalaplayground.antipatterns

//
// Anti-pattern: Using Strings for structured data
// 1. Problem: Strings are not type-safe and can lead to runtime errors.
//
def presentData(): Unit = {
  val personData = "John Doe,30,New York"
  val parts = personData.split(",")
  val name = parts(0)
  val age = parts(1).toInt
  val city = parts(2)
  println(s"Name: $name, Age: $age, City: $city")
}

object StringsAntiPattern extends App{
  presentData()
}
