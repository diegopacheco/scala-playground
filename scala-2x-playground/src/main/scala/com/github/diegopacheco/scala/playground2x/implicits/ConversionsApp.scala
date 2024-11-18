package com.github.diegopacheco.scala.playground2x.implicits

import scala.language.implicitConversions

case class Centimeters(value: Double) extends AnyVal
case class Meters(value: Double) extends AnyVal

//
// implicit conversion limitations
// 1. They cannot take multiple non-implicit arguments
// 2. They cannot chain multiple implicit conversions
//
object Conversions {
  implicit def centimetersToMeters(c: Centimeters): Meters = Meters(c.value / 100)
  implicit def metersToCentimeters(m: Meters): Centimeters = Centimeters(m.value * 100)
}

object ConversionsApp extends App{
  import Conversions._

  val centimeters: Centimeters = Meters(2.5)
  val meters: Meters = Centimeters(250)
  println(centimeters)
  println(meters)

}
