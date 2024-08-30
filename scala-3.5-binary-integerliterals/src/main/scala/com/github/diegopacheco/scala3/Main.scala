package com.github.diegopacheco.scala3

object Main {
  def main(args: Array[String]): Unit = {
    assert(0B1 == 1)
    assert(0B10 == 2)
    assert(0B_1000_0010 == 130)
    assert(0B_1000_0010 == 0x82)

    println(0B1)
    println(0B_1000_0010)

  }
}
