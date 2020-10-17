package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.ActorRef

trait Product
case class Coke() extends Product
case class Sprite() extends Product
case class ChocolateBar() extends Product
case class GummyBearCandy() extends Product

trait Receipt
case class ProductReceipt(product:Product) extends Receipt

trait Request
case class ProductRequest(product:Product,buyer:ActorRef[ProductReceipt])

case class Start(buyer:String,product:Product)