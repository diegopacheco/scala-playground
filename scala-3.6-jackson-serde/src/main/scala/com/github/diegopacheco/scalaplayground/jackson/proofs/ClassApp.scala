package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{fromJson, toJson}

class Person(
  val name: String,
  val age: Int
) {
  override def toString: String = s"Person(name=$name, age=$age)"
}

object ClassApp extends App{

  private val p = new Person("John Doe", 50)
  val json = toJson(p)
  println(json)

  private val p2 = fromJson(json, classOf[Person])
  println(p2)

  println(p == p2)

}
