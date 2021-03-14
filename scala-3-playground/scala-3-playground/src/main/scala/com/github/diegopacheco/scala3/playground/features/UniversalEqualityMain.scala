package com.github.diegopacheco.scala3.playground.features

object UniversalEqualityMain extends App{

  case class Cat(name: String)
  
  val melina1 = Cat("Melina")
  val melina2 = Cat("Melina")
  
  val same = melina1==melina2
  println(same)
  
}
