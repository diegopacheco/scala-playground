package com.github.diegopacheco.scala3.playground.features

object UniversalEqualityMain2 extends App {

  import scala.language.strictEquality

  // case class Dog(name: String)

  val rover = Dog("Rover")
  val fido = Dog("Rover")
  // println(rover == fido) //Values of types com.github.diegopacheco.scala3.playground.features.UniversalEqualityMain2.Dog and com.github.diegopacheco.scala3.playground.features.UniversalEqualityMain2.Dog cannot be compared with == or !=

  // Option 1
  case class Dog(name: String) derives CanEqual
  println(rover == fido)

  // Option 2
  //case class Dog(name: String)
  //given CanEqual[Dog, Dog] = CanEqual.derived
  
}
