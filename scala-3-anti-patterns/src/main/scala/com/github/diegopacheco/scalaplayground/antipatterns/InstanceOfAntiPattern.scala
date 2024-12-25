package com.github.diegopacheco.scalaplayground.antipatterns

// Anti-pattern: Using `isInstanceOf` and `asInstanceOf`
// 1. It's not type-safe
// 2. It's not idiomatic Scala
// 3. It's not functional
//
def presentData(): Unit = {
  val x: Any = "Hello"
  if (x.isInstanceOf[String]) {
    val s = x.asInstanceOf[String]
    println(s)
  }
}

object InstanceOfAntiPattern extends App{
  presentData()
}
