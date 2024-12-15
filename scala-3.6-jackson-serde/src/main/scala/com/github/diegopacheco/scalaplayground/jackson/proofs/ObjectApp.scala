package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{fromJson,toJson}

object Highlander {
  val name = "Connor MacLeod"
  val age = 500
  def fight(): Unit = println("There can be only one!")
  override def toString: String = s"Highlander(name=$name, age=$age)"
}

object ObjectApp extends App{

  val json = toJson(Highlander)
  println(json)

  private val highlander = fromJson(json, Highlander.getClass)
  println(highlander)

}
