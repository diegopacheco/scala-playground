package com.github.diegopacheco.scala.playground.akka

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.actorRef2Scala
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.Put
import akka.cluster.pubsub.DistributedPubSubMediator.Send
import akka.cluster.pubsub.DistributedPubSubMediator
import com.typesafe.config.ConfigFactory
import akka.cluster.Cluster
import akka.actor.ActorSystem
import akka.actor.Props

class Destination extends Actor with ActorLogging {
  import DistributedPubSubMediator.Put
  val mediator = DistributedPubSub(context.system).mediator

  // register to the path
  mediator ! Put(self)

  def receive = {
    case s: String =>
      log.info("Got MSG:{} Me:{}:{}", s,self.path,context.system.settings.config.getString("akka.remote.netty.tcp.port"))
  }
}

class Sender extends Actor {
  import DistributedPubSubMediator.Send

  // activate the extension
  val mediator = DistributedPubSub(context.system).mediator

  def receive = {
    case in: String =>
      val out = in.toUpperCase
      mediator ! Send(path = "/user/destination", msg = out, localAffinity = true)
  }
}

object SendApp extends App {

  def bootup(port: String, role: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"akka.cluster.roles = [$role]"))
      .withFallback(ConfigFactory.parseString(s"""
                        akka.actor.deployment {
                       }
                    """)).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val system1 = bootup("2551", "compute")
  val system2 = bootup("2552", "compute")
  val system3 = bootup("0", "compute")
  Cluster(system1)
  Cluster(system2)
  Cluster(system3)
  Thread.sleep(5000)
  
  val d1 = system1.actorOf(Props[Destination], "destination")
  val d2 = system2.actorOf(Props[Destination], "destination")

  val sender = system3.actorOf(Props[Sender], "sender")

  // after a while the destinations are replicated
  Thread.sleep(6000)
  
  sender ! "hello"
  sender ! "hello, there?"
  sender ! "hello, Dude?"
  sender ! "Arghhh Cabron..."

}