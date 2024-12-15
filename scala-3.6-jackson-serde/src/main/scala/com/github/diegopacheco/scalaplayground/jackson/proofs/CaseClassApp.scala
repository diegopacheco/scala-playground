package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{toJson,fromJson}

case class Cat(name: String, age: Int, color: String)

object CaseClassApp extends App{

  private val cat = Cat("Mittens", 5, "black")
  val json = toJson(cat)
  println(json)

  private val cat2 = fromJson(json, classOf[Cat])
  println(cat2)
  println(cat == cat2)

}
