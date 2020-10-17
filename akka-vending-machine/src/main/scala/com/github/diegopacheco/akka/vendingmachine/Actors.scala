package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorRef, Behavior, Signal}

object VendingMachineActor {
  def apply(): Behavior[ProductRequest] =
    Behaviors.setup(context => new VendingMachineActor(context))
}

class VendingMachineActor(context: ActorContext[ProductRequest]) extends AbstractBehavior[ProductRequest](context) {
  override def onMessage(request: ProductRequest): Behavior[ProductRequest] = {
    println(s"[${this.context.self.path.name}] A sale is comming up... ")
    request match {
      case ProductRequest(c,reciptTo) =>
        println(s"[${this.context.self.path.name}] Coke is always a good chose!")
        reciptTo ! ProductReceipt(c)
        Behaviors.same
      case ProductRequest(s,reciptTo) =>
        println(s"[${this.context.self.path.name}] Sprit is for the strong ones!")
        reciptTo ! ProductReceipt(s)
        Behaviors.same
      case ProductRequest(c,reciptTo) =>
        println(s"[${this.context.self.path.name}] Yummy! Chocolate is a good call!")
        reciptTo ! ProductReceipt(c)
        Behaviors.same
      case ProductRequest(g,reciptTo) =>
        println(s"[${this.context.self.path.name}] Gummie Bear Oh oh!")
        reciptTo ! ProductReceipt(g)
        Behaviors.same
  }
  }
}

object BuyerActor {
  def apply(): Behavior[ProductReceipt] =
    Behaviors.setup(context => new BuyerActor(context))
}

class BuyerActor(context: ActorContext[ProductReceipt]) extends AbstractBehavior[ProductReceipt](context) {
  override def onMessage(request: ProductReceipt): Behavior[ProductReceipt] =
    request match {
      case ProductReceipt(p:Product) =>
        println(s"[${this.context.self.path.name}] OK - we got a receipt for a ${p}")
        Behaviors.same
    }
}

object BootStrappingActor {
  def apply(): Behavior[Start] =
    Behaviors.setup(context => new BootStrappingActor(context))
}

class BootStrappingActor(context: ActorContext[Start]) extends AbstractBehavior[Start](context) {
  override def onMessage(msg:Start): Behavior[Start] =
    msg match {
      case _ =>
        val buyer:ActorRef[ProductReceipt] = context.spawn(BuyerActor(),"buyer-actor-" + msg.buyer)
        println(s"[${this.context.self.path.name}] Got A buyer ${buyer.ref}")

        val vending:ActorRef[ProductRequest] = context.spawn(VendingMachineActor(),"vending-machine-actor-" + msg.product.getClass.getSimpleName)
        println(s"[${this.context.self.path.name}] Got A VendingMachine ${vending.ref}")

        vending ! ProductRequest(Coke(),buyer)
        Behaviors.same
    }
}

