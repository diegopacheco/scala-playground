package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{toJson, fromJson}

object Planet extends Enumeration {
  val Earth, Mars, Venus = Value
}

object EnumApp extends App{

  private val planet = Planet.Earth
  val json = toJson(planet)
  println(json)

  private val planet2 = fromJson(json, classOf[Planet.Value])
  println(planet2)
  println(planet == planet2)
}
