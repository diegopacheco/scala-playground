package com.github.diegopacheco.scala3.playground.features

object TraitsMain extends App {
  
  trait Animal:
    def speak():Unit
  
  trait HasTail:
    def wagTail():Unit
  
  class Dog extends Animal, HasTail:
    def speak() = println("Woof")
    def wagTail() = println("⎞⎜⎛  ⎞⎜⎛")
  
  val dog = Dog()
  println(s"${dog.speak()} ${dog.wagTail()}")
  
}
