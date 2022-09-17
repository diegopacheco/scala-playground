package com.github.diegopacheco.scala3.basic.ifs

object IFsLineApp extends App{
  val x = 10
  val result = if x < 0 then -x else x
  println(s"${x} is ${result}")
}
