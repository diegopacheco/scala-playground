package com.github.diegopacheco.scala3.playground.features

class PersonRecord(elems: (String, Any)*) extends Selectable:
  private val fields = elems.toMap
  def selectDynamic(name: String): Any = fields(name)

type Person = PersonRecord {
  val name: String
  val age: Int
}

object StructuralTypeMainApp extends App {
  
  val person = PersonRecord(
    "name" -> "Emma",
    "age" -> 42
  ).asInstanceOf[Person]

  println(s"${person.name} is ${person.age} years old. ${person} \n")
  println(s"name: ${person.selectDynamic("name").asInstanceOf[String]} - " +
          s"age:  ${person.selectDynamic("age").asInstanceOf[Int]}")
  
}
