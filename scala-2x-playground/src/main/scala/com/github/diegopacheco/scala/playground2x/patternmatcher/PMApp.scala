package com.github.diegopacheco.scala.playground2x.patternmatcher

class Person

object PMApp extends App{

  def getClassAsString(x: Any):String = x match {
    case s: String => s + " is a String"
    case i: Int => "Int"
    case f: Float => "Float"
    case l: List[_] => "List"
    case p: Person => "Person"
    case _ => "Unknown"
  }

  println(getClassAsString("Hello"))
  println(getClassAsString(1))
  println(getClassAsString(1.0f))
  println(getClassAsString(List(1,2,3)))
  println(getClassAsString(new Person))

}
