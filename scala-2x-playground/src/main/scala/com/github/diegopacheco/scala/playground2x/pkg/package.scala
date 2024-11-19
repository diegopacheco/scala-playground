package com.github.diegopacheco.scala.playground2x

package object pkg {
  val PI = 3.14

  def sum(a: Int, b: Int): Int = a + b
}

import com.github.diegopacheco.scala.playground2x.pkg._

object Main {
  def main(args: Array[String]): Unit = {
    println(PI)
    println(sum(1, PI.toInt))
  }
}