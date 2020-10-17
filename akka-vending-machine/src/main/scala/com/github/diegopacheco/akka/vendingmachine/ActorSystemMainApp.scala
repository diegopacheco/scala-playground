package com.github.diegopacheco.akka.vendingmachine

import akka.actor.typed.{ActorSystem}

object ActorSystemMainApp {
  def main(args:Array[String]):Unit = {
    implicit val actorSystem = ActorSystem(BootStrappingActor(), "VendingSystem")
    val start = Start("Diego",Coke())
    actorSystem ! start
  }
}
