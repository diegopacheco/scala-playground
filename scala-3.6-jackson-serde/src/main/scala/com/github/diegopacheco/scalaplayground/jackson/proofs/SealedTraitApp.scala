package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{fromJson, toJson}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(Array(
  new JsonSubTypes.Type(value = classOf[MilkWay.type], name = "MilkWay"),
  new JsonSubTypes.Type(value = classOf[Andromeda.type], name = "Andromeda"),
  new JsonSubTypes.Type(value = classOf[Triangulum.type], name = "Triangulum")
))
sealed trait Universe
case object MilkWay extends Universe
case object Andromeda extends Universe
case object Triangulum extends Universe

object SealedTraitApp extends App{

  private val universe = MilkWay
  val json = toJson(universe)
  println(json)

  private val universe2 = fromJson(json, classOf[Universe])
  println(universe2)
  println(universe == universe2)

}
