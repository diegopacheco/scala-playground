package com.github.diegopacheco.scala.playground.akka.cluster.supervisor

import scala.concurrent.duration.DurationInt

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.BackoffSupervisor

class EchoActor extends Actor {
  def receive = {
    case s:String => println("Echo: " + s)
  }
}

object SupervisorApp extends App {

  val childProps = Props(classOf[EchoActor])

  val supervisor = BackoffSupervisor.props(
    childProps,
    childName = "myEcho",
    minBackoff = 3.seconds,
    maxBackoff = 30.seconds,
    randomFactor = 0.2) // adds 20% "noise" to vary the intervals slightly

  val system = ActorSystem("MySystem")
  
  val sup = system.actorOf(supervisor, name = "echoSupervisor")
  println(system)
  println(sup)
  println(system.actorSelection("/user/echoSupervisor"))
  
  sup.tell("YO MAN", sup)
    
}