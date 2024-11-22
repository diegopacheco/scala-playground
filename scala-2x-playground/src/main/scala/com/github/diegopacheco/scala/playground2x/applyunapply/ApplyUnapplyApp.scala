package com.github.diegopacheco.scala.playground2x.applyunapply

import com.github.diegopacheco.scala.playground2x.applyunapply.ApplyUnapplyApp.Person.unapply

object ApplyUnapplyApp extends App{

  case class Person(name: String, age: Int)

  object Person {
    def apply(name: String): Person = new Person(name, 0)
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
  }

  // Using the custom apply method
  val person1 = Person("John")
  println(person1)  // Output: Person(John,0)

  // Using the default apply method
  val person2 = Person("Jane", 25)
  println(person2)  // Output: Person(Jane,25)

  person2 match {
    case Person(name, age) => println(s"Name: $name, Age: $age")
    case _ => println("No match")
  }

  unapply(person1) match {
    case Some((name, age)) => println(s"Name: $name, Age: $age")
    case None => println("No match")
  }

}
