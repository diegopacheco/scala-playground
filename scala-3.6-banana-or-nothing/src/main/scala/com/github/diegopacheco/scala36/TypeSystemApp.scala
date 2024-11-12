package com.github.diegopacheco.scala36

trait Fruit
case object Banana extends Fruit
case object PeeledBanana extends Fruit
type BananaOrNothing = PeeledBanana.type | List[Nothing]

object FruitOps{
  def peel(fruit: Fruit): BananaOrNothing = fruit match {
    case Banana => PeeledBanana
    case _ => List.empty
  }
}

object TypeSystemApp extends App{
  private val banana: BananaOrNothing = FruitOps.peel(Banana)
  println(banana)
  println(FruitOps.peel(null))
}
