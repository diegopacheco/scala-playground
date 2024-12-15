package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{fromJson, toJson}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(Array(
  new JsonSubTypes.Type(value = classOf[Hot], name = "HOT"),
  new JsonSubTypes.Type(value = classOf[Mild], name = "MILD"),
  new JsonSubTypes.Type(value = classOf[No], name = "NO_SAUCE")
))
sealed trait Sauce
case class Hot() extends Sauce
case class Mild() extends Sauce
case class No() extends Sauce

object SealedTraitCaseClassApp extends App{
    private val sauce = Hot()
    val json = toJson(sauce)
    println(json)

    private val sauce2 = fromJson(json, classOf[Sauce])
    println(sauce2)
    println(sauce == sauce2)
}
