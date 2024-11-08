package com.github.diegopacheco.scalaplayground.enumeratum

import enumeratum.*

import scala.util.Try

sealed trait Greeting extends EnumEntry

object Greeting extends Enum[Greeting] {
  /*
   `findValues` is a protected method that invokes a macro to find all `Greeting` object declarations inside an `Enum`
   You use it to implement the `val values` member
  */
  val values = findValues

  case object Hello extends Greeting
  case object GoodBye extends Greeting
  case object Hi extends Greeting
  case object Bye extends Greeting
}

object EnumeratumApplication extends App {

  println(Greeting.withName("Hello"))

  println(Try(Greeting.withName("Haro")))

  println(Greeting.withNameOption("Hello"))

  println(Greeting.withNameInsensitive("HeLLo"))

  println(Greeting.withNameInsensitiveOption("HeLLo"))

}
