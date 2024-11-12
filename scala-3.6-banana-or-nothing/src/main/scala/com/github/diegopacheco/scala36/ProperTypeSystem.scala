package com.github.diegopacheco.scala36

object ProperTypeSystem extends App {
  trait Fruit
  case object Banana extends Fruit
  case object PeeledBanana extends Fruit
  type BananaOrNothing = Option[Banana.type | PeeledBanana.type]

  object FruitOps {
    def peel(fruit: Fruit): BananaOrNothing = fruit match {
      case Banana => Some(PeeledBanana)
      case _ => None
    }
  }

  private val banana: BananaOrNothing = FruitOps.peel(Banana)
  println(banana)
  println(FruitOps.peel(null))
}
