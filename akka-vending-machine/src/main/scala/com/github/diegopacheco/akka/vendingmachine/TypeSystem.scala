package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.ActorRef

trait Product {
  def getMessage():String
}
case class Coke() extends Product{
  override def getMessage() = "Coke is always a good chose!"
}
case class Sprite() extends Product{
  override def getMessage() = "Sprite is for the strong ones!"
}
case class ChocolateBar() extends Product{
  override def getMessage() = "Yummy! Chocolate is a good call!"
}
case class GummyBearCandy() extends Product{
  override def getMessage() = "Gummy Bear Oh oh!"
}

trait Receipt
case class ProductReceipt(product:Product) extends Receipt
case class OutOfStock(product:Product) extends Receipt

trait Request
case class ProductRequest(product:Product,buyer:ActorRef[Receipt])

case class Order(buyer:String, product:Product)