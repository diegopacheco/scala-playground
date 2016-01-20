package com.github.diegopacheco.scala.playground.fp

object Foreach extends App {

  def triangle(side: Int): Unit = {
    (1 to side) foreach { row =>
      (1 to row) foreach { col =>
        print("*")
      }
      println("")
    }
  }
  
  triangle(5)

}