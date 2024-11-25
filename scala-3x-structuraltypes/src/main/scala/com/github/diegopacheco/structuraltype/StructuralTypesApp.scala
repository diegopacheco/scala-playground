package com.github.diegopacheco.structuraltype

//
// Structural Types - https://docs.scala-lang.org/scala3/book/types-structural.html
//
class Record(elems: (String, Any)*) extends Selectable:
  private val fields = elems.toMap
  def selectDynamic(name: String): Any = fields(name)

type Person = Record {
  val name: String
  val age: Int
}

object StructuralTypesApp extends App:
  val person = Record(
    "name" -> "Emma",
    "age" -> 42
  ).asInstanceOf[Person]

  println(s"${person.name} is ${person.age} years old.")