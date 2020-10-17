package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorRef, Behavior}

object Actors {
  def fmt(context: ActorContext[_]): String = s"[${context.self.path.name}] "
}
import Actors._

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
    println(s"${fmt(context)} A sale is coming up... ")

    request match {
      case ProductRequest(product, receiptTo) =>
        val item = product.getClass.getSimpleName
        stock.get(item) match {
          case Some(i) if i - 1 >= 0 => {
            stock(item) = i - 1
            println(s"${fmt(context)} ${product.getMessage()}")
            receiptTo ! ProductReceipt(product)
          }
          case Some(i) => {
            println(s"${fmt(context)} Out of Stock for ${product}")
            receiptTo ! OutOfStock(product)
          }
          case None => {
            println(s"${fmt(context)} We don't sell this item [$item].Sorry!")
            receiptTo ! ItemOutOfCatalog(product)
          }
        }

        println(s"${fmt(context)} Sock: ${stock}")
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
      case ProductReceipt(p: Product) =>
        println(s"${fmt(context)} OK - we got a receipt for a ${p}")
        Behaviors.same
      case OutOfStock(p: Product) =>
        println(s"${fmt(context)} Oh no! My favorite ${p} is not available :( ")
        Behaviors.same
      case ItemOutOfCatalog(product) => {
        println(s"${fmt(context)} Oh no! The Vending machines don't sell ${product} Ouch!")
        Behaviors.same
      }
    }
}

object BootStrappingActor {
  def apply(): Behavior[Order] =
    Behaviors.setup(context => new BootStrappingActor(context))
}

class BootStrappingActor(context: ActorContext[Order]) extends AbstractBehavior[Order](context) {

  val vending: ActorRef[ProductRequest] = context.spawn(VendingMachineActor(), "vending-machine-actor")
  println(s"${fmt(context)} Got A VendingMachine ${vending.ref}")

  override def onMessage(msg: Order): Behavior[Order] =
    msg match {
      case Order(_,_) =>
        val buyer: ActorRef[Receipt] = context.spawn(BuyerActor(), "buyer-actor-" + msg.buyer + s"-${System.nanoTime()}")
        println(s"${fmt(context)} Got A buyer ${buyer.ref}")

        vending ! ProductRequest(msg.product, buyer)
        Behaviors.same
    }
}

