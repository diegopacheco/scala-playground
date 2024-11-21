package com.github.diegopacheco.scala3x.playground.pratical

import scala.language.strictEquality

case class Person(name: String, age: Int)
case class Car(brand: String, model: String)

object MultiverseEqualityOps:
  given CanEqual[Car, Car] = CanEqual.derived
  given CanEqual[Person, Person] = CanEqual.derived


object MultiverseEquality extends App:
  import MultiverseEqualityOps.given

  val person = Person("Alice", 30)
  val car = Car("Toyota", "Corolla")
  val anotherPerson = Person("Alice", 30)
  println(person == anotherPerson)

  // not allowed
  // println(person == car)