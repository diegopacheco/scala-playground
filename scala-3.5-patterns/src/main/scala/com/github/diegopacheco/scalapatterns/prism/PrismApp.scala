package com.github.diegopacheco.scalapatterns.prism

/**
 * A Prism is a functional programming concept used to work with sum types
 * (also known as tagged unions or algebraic data types).
 * It allows you to focus on a specific case of a sum type,
 * enabling safe extraction and modification of that case.
 */

sealed trait Json
case class JsString(value: String) extends Json
case class JsNumber(value: Double) extends Json

case class Prism[S, A](getOption: S => Option[A], reverseGet: A => S)

object JsonPrism {
  val jsStringPrism: Prism[Json, String] = Prism[Json, String](
    getOption = {
      case JsString(value) => Some(value)
      case _ => None
    },
    reverseGet = JsString
  )
}

object PrismApp extends App{
  val json: Json = JsString("Hello")
  val result = JsonPrism.jsStringPrism.getOption(json)
  println(result) // Some(Hello)
}
