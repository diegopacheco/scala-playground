package com.github.diegopacheco.scalaplayground.antipatterns

//
// Anti-Pattern: Any
// 1. Using Any as input or return type
//

def getValue(flag: Boolean): Any = {
  if (flag) 42 else "forty-two" // Anti-pattern
}

object AnyAntiPattern extends App{
  println(getValue(true))
  println(getValue(false))
}
