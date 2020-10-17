package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorRef, Behavior}

object VendingMachineActor {
  def apply(): Behavior[ProductRequest] =
    Behaviors.setup(context => new VendingMachineActor(context))
}

class VendingMachineActor(context: ActorContext[ProductRequest]) extends AbstractBehavior[ProductRequest](context) {

  var stock = collection.mutable.Map(
    "Coke" -> 2,
    "Sprite" -> 1,
    "ChocolateBar" -> 1,
    "GummyBearCandy" -> 1
  )

  override def onMessage(request: ProductRequest): Behavior[ProductRequest] = {
    println(s"[${this.context.self.path.name}] A sale is comming up... ")
    request match {
      case ProductRequest(product, receiptTo) =>
        val item = product.getClass.getSimpleName
        if (stock.get(item).get - 1 >= 0) {
          stock(item) = stock.get(item).get - 1
          println(s"[${this.context.self.path.name}] ${product.getMessage()}")
          receiptTo ! ProductReceipt(product)
        } else {
          println(s"[${this.context.self.path.name}] Out of Stock for ${product}")
          receiptTo ! OutOfStock(product)
        }
        println(s"[${this.context.self.path.name}] Sock: ${stock}")
        Behaviors.same
    }
  }
}

object BuyerActor {
  def apply(): Behavior[Receipt] =
    Behaviors.setup(context => new BuyerActor(context))
}

class BuyerActor(context: ActorContext[Receipt]) extends AbstractBehavior[Receipt](context) {
  override def onMessage(request: Receipt): Behavior[Receipt] =
    request match {
      case ProductReceipt(p:Product) =>
        println(s"[${this.context.self.path.name}] OK - we got a receipt for a ${p}")
        Behaviors.same
      case OutOfStock(p:Product) =>
        println(s"[${this.context.self.path.name}] Oh no! My favorite ${p} is not available :( ")
        Behaviors.same
    }
}

object BootStrappingActor {
  def apply(): Behavior[Order] =
    Behaviors.setup(context => new BootStrappingActor(context))
}

class BootStrappingActor(context: ActorContext[Order]) extends AbstractBehavior[Order](context) {

  val vending:ActorRef[ProductRequest] = context.spawn(VendingMachineActor(),"vending-machine-actor")
  println(s"[${this.context.self.path.name}] Got A VendingMachine ${vending.ref}")

  override def onMessage(msg:Order): Behavior[Order] =
    msg match {
      case _ =>
        val buyer:ActorRef[Receipt] = context.spawn(BuyerActor(),"buyer-actor-" + msg.buyer + s"-${System.nanoTime()}")
        println(s"[${this.context.self.path.name}] Got A buyer ${buyer.ref}")

        vending ! ProductRequest(msg.product,buyer)
        Behaviors.same
    }
}

