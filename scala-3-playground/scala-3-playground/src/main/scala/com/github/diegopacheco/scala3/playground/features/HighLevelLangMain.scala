package com.github.diegopacheco.scala3.playground.features

import scala.collection.mutable.ListBuffer

object HighLevelLangMain extends App {
  
  def double(ints:List[Int]): List[Int] = {
    val buffer = new ListBuffer[Int]()
    for (i <- ints){
      buffer += i * 2
    }
    buffer.toList
  }
  
  val newDoubles = double(List(1,2,3,4,5,6))
  println(s"New Doubles == ${newDoubles}")
  
}
