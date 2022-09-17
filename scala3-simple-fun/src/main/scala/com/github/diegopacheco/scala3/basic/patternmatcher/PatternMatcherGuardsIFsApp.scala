package com.github.diegopacheco.scala3.basic.patternmatcher

object PatternMatcherGuardsIFsApp extends App{
  def process(i:Int):Unit = i match
    case 1 => println("one, a lonely number")
    case x if x == 2 || x == 3 => println("two’s company, three’s a crowd")
    case x if x > 3 => println("4+, that’s a party")
    case _ => println("i’m guessing your number is zero or less")

  process(3)
}
