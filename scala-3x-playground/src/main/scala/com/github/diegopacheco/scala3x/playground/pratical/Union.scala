package com.github.diegopacheco.scala3x.playground.pratical

object Union extends App:
  def playstation(version: Int | String): String = version match
    case i: Int => s"Integer: $i"
    case s: String => s"String: $s"

  val result1 = playstation(5)     // "Integer: 5"
  val result2 = playstation("ps4") // "String: ps4"
  println(s"result1: ${result1} - result2: ${result2}")