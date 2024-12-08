package com.github.diegopacheco.scalaplayground.traffic

sealed trait TrafficLight(val prev:TrafficLight, val next: TrafficLight)
case object Red extends TrafficLight(Green, Yellow)
case object Green extends TrafficLight(Red, Yellow)
case object Yellow extends TrafficLight(Red, Green)

object TrafficApp extends App {
  private def run(light: TrafficLight): Unit = {
    println(s"Current state: ${light.getClass.getSimpleName}")
    val nextLight = light.next
    println(s"Next state: ${nextLight.getClass.getSimpleName}")
  }

  private val redLight = Red
  run(redLight)
  run(redLight.next)
  run(redLight.next.next)
  run(redLight.next.next.next)
}