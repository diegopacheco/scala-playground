package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.{ActorSystem}

object ActorSystemMainApp {
  def main(args:Array[String]):Unit = {
    implicit val actorSystem = ActorSystem(BootStrappingActor(), "VendingSystem")
    val firstOrder = Order("Diego",Coke())
    actorSystem ! firstOrder
    actorSystem ! firstOrder
    actorSystem ! firstOrder
    actorSystem ! Order("Melina",Sprite())
    actorSystem ! Order("Gandalfy",GummyBearCandy())
  }
}
