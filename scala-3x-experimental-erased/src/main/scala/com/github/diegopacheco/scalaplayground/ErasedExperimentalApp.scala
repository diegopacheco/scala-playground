package com.github.diegopacheco.scalaplayground

import scala.language.experimental.erasedDefinitions

//
// https://docs.scala-lang.org/scala3/reference/experimental/erased-defs.html
//
class Logging {
  def exampleMethod(erased x: Int): Unit = {
    println("This method has an erased parameter.")
  }
}

object ErasedExperimentalApp extends App:
  val logging = Logging()
  // The argument is provided but will be erased at compile time
  logging.exampleMethod(42)
