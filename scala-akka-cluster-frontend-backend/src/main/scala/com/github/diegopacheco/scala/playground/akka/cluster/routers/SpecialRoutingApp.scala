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
import akka.routing.Broadcast
import akka.actor.PoisonPill

class PActor extends Actor {
  def receive = {
    case s: String =>
      println("Print: " + s + " - " + self.path + " - " + context.dispatcher)
  }
}

object SpecialRoutingApp extends App {

  def bootup(port: String, dispatcher: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"""
                        akka.actor.deployment {
                          /router1 {
                              router = round-robin-pool
                              nr-of-instances = 5
                          }
                        }
                    """)).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val system = bootup("2551", "compute")
  val router1: ActorRef = system.actorOf(FromConfig.props(Props[PActor]), "router1")
  for (i <- 1 to 20) router1 ! s"Hi there? $i "
  
  router1 ! Broadcast("Que Haces? Akka o Plomo?")
  router1 ! PoisonPill
  
}
