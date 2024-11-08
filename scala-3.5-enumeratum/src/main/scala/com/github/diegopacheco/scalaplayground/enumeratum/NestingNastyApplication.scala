package com.github.diegopacheco.scalaplayground.enumeratum

import enumeratum._

sealed trait Nesting extends EnumEntry

object Nesting extends Enum[Nesting] {
  val values = findValues

  case object Hello extends Nesting

  object others {
    case object GoodBye extends Nesting
  }

  case object Hi extends Nesting

  class InnerClass {
    case object NotFound extends Nesting
  }
}

object NestingNastyApplication extends App{
  println(Nesting.values) // Vector(Hello, GoodBye, Hi)
}
