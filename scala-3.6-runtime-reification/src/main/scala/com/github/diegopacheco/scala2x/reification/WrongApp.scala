package com.github.diegopacheco.scala2x.reification

object WrongApp extends App{
  println(describeType(List("hi")))

  def describeType(xs: Any): String =
    xs match {
      case xs: List[Int]    => "xs is List[Int]"
      case xs: List[String] => "xs is List[String]"
      case xs               => "xs is unknown"
    }
}
