package com.github.diegopacheco.scalaplayground.jackson.serde

import com.fasterxml.jackson.databind.{DeserializationFeature, SerializationFeature}
import com.github.diegopacheco.scalaplayground.jackson.proofs.PizzaModule.CustomPizzaModule

object SerializationService {
  import com.fasterxml.jackson.databind.ObjectMapper
  import com.fasterxml.jackson.module.scala.DefaultScalaModule

  private val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.registerModule(new CustomPizzaModule)
  mapper.enable(SerializationFeature.INDENT_OUTPUT) // for pretty print
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  
  def toJson[T](value: T): String = {
    mapper.writeValueAsString(value)
  }

  def fromJson[T](json: String, clazz: Class[T]): T = {
    mapper.readValue(json, clazz)
  }
}
