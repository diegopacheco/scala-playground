package com.github.diegopacheco.akkabasic.actors

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior}

object PrintMyActorRefActor {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new PrintMyActorRefActor(context))
}

class PrintMyActorRefActor(context: ActorContext[String]) extends AbstractBehavior[String](context) {
  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "printit" =>
        val secondRef = context.spawn(Behaviors.empty[String], "second-actor")
        println(s"Second: $secondRef")
        this
    }
}

object BootstrapActor {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new BootstrapActor(context))

}

class BootstrapActor(context: ActorContext[String]) extends AbstractBehavior[String](context) {
  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "start" =>
        val firstRef = context.spawn(PrintMyActorRefActor(), "first-actor")
        println(s"First: $firstRef")
        firstRef ! "printit"
        this
    }
}

object MainApp  {
  def main(args:Array[String]):Unit = {
    val testSystem = ActorSystem(BootstrapActor(), "testSystem")
    testSystem ! "start"
  }
}
