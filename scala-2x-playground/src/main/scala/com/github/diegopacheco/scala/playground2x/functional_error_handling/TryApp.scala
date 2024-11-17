package com.github.diegopacheco.scala.playground2x.functional_error_handling

import scala.util.{Try,Success,Failure}

object TryApp extends App{

  def toInt(s: String): Try[Int] = Try {
    Integer.parseInt(s.trim)
  }

  def toIntShort(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  println(toInt("1"))
  println(toInt("1x"))

  println(toIntShort("2"))
  println(toIntShort("2x"))

  toInt("oopsy") match {
    case Success(i) => println(i)
    case Failure(s) => println(s"Failed. Reason: $s")
  }

}
