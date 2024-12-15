package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{toJson,fromJson}

case class Ticket(
 name:String,
 criticality:Option[Int],
)

object OptionApp extends App{

  private val t1 = Ticket("Build scala apps",Some(1))
  val json = toJson(t1)
  println(json)

  private val t2 = fromJson(json,classOf[Ticket])
  println(t2)
  println(t1 == t2)

  private val t3 = Ticket("Build Zig apps",None)
  private val json2 = toJson(t3)
  println(json2)
  private val t4 = fromJson(json2,classOf[Ticket])
  println(t4)
}
