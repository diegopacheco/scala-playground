package com.github.diegopacheco.scalaplayground.enumeratum

enum Greeting3x:
  case Hello, GoodBye, Hi, Bye

// exhaustive check
def greet(g: Greeting3x): String = g match
  case Greeting3x.Hello => "Hello"
  case Greeting3x.GoodBye => "GoodBye"
  case Greeting3x.Hi => "Hi"
  case Greeting3x.Bye => "Bye"

object Scala3x extends App{
  println(greet(Greeting3x.Hello))
}
