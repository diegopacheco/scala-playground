package com.github.diegopacheco.scala.playground2x.richwrappers

object RichIntImplicits {
  implicit class RichInt(wrapped: Int) {
    val digits: Int = wrapped.toString.length
  }
}

object WrappersApp extends App{
  import RichIntImplicits._
  println(s"123 digits: ${123.digits}")
}
