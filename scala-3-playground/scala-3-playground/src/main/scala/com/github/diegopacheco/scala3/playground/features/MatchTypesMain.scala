package com.github.diegopacheco.scala3.playground.features

@main def MatchTypesMain():Unit = {

  type Elem[X] = X match
    case String => Char
    case Array[t] => t
    case Iterable[t] => t
  
  val c:Elem[String] = 'a'
  println(c)

  val arr:Elem[Array[Int]] = 1
  println(arr)
  
}
