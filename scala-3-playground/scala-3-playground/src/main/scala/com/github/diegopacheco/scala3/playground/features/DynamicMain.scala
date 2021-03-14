package com.github.diegopacheco.scala3.playground.features

object DynamicMain extends App {
  
  class Person(val name:String,val lastName:String){
    override def toString():String = name + " " + lastName
  }
  val ints = List(1,2,3,4,5,6,7,8,9,10)
  
  val s = "Hello"
  val p = Person("Al", "Pacino")
  val sum = ints.reduceLeft(_+_)
  val y = for i <- ints yield i * 2
  val z = ints.filter(_>7)
    .filter(_<12)
    .map(_*2)
  
  println(s"s == ${s} ")
  println(s"p == ${p} ")
  println(s"sum == ${sum} ")
  println(s"y == ${y} ")
  println(s"z == ${z} ")

}
