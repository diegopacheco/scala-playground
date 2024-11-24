package com.github.diegopacheco.scala2x.reification

object RightApp extends App{
  println(describeType(List("hi")))

  def describeType[A1: Manifest](xs: A1): String = {
    val m = implicitly[Manifest[A1]]
    val mli = implicitly[Manifest[List[Int]]]
    val mls = implicitly[Manifest[List[String]]]
    xs match {
      case xs: List[Int]    if m == mli => "xs is List[Int]"
      case xs: List[String] if m == mls => "xs is List[String]"
    }
  }
}
