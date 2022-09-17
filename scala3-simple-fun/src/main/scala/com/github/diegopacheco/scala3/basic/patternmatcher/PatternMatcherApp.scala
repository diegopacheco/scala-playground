package com.github.diegopacheco.scala3.basic.patternmatcher

object PatternMatcherApp extends App{
  val evenOrOdd = 10 match
    case 1 | 3 | 5 | 7 | 9 => "odd"
    case 2 | 4 | 6 | 8 | 10 => "even"
    case _ => "some other number"
  println(s"${evenOrOdd}")
}
