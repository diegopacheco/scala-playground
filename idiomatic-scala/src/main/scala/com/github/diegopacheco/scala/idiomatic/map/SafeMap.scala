package com.github.diegopacheco.scala.idiomatic.map

object SafeMap extends App {
  
  def toInt(in: String): Option[Int] = {
    try {
        Some(Integer.parseInt(in.trim))
    } catch {
        case e: NumberFormatException => None
    }
  }
  
  val things  = List("banana", "1", "WAT", "2", "false", "3", "4", "5", "HA","6")
  val numbers = things.flatMap(toInt).sum 
  println(numbers)
  
}