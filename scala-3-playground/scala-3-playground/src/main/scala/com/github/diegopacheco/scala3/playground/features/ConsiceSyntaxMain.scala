package com.github.diegopacheco.scala3.playground.features

object ConsiceSyntaxMain extends App{
  
  class Person(val name:String, val lastName:String)
  
  val diego = Person("Diego","Pacheco")
  println(s"Thats me: ${diego.name} ${diego.lastName}")
  
  val numbers = List(1,2,3,4,5,6,7,8,9,10)
  var result = numbers.map( i=> i*2 )
  println(s"Result is ${result}")

  result = numbers.map(_*2)
  println(s"Result is ${result}")

  result = numbers.filter( i => i > 5 )
  println(s"Result is ${result}")

  result = numbers.filter( _ > 5 )
  println(s"Result is ${result}")
  
}
