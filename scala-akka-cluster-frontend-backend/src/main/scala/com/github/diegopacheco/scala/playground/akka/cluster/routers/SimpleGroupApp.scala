package com.github.diegopacheco.scala.playground.akka.cluster.routers

import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy
import com.typesafe.config.ConfigFactory
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.routing.FromConfig
import akka.routing.RoundRobinPool
import akka.actor.Props

class PrinterActorGroup extends Actor {
  def receive = {
    case s: String =>
      println("Print: " + s + " - " + self.path + " - " + context.dispatcher)
  }
}

object SimpleGroupApp extends App {

  def bootup(port: String, dispatcher: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"""
                        akka.actor.deployment {
                           /groupRouter {
                               router = round-robin-group
                               routees.paths = ["/user/p1", "/user/p2", "/user/p3"]
                           }
                        }
                    """)).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val system = bootup("2551", "compute")
  val router1: ActorRef = system.actorOf(FromConfig.props(Props[PrinterActorGroup]), "groupRouter")
  
  system.actorOf(Props[PrinterActorGroup], name = "p1")
  system.actorOf(Props[PrinterActorGroup], name = "p2")
  system.actorOf(Props[PrinterActorGroup], name = "p3")
  
  for (i <- 1 to 20) router1 ! s"Hi there? $i "
  
  
}
