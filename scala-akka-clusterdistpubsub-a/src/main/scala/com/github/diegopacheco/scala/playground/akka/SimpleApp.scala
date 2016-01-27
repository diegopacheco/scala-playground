package com.github.diegopacheco.scala.playground.akka

import com.typesafe.config.ConfigFactory
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorSystem
import akka.actor.actorRef2Scala
import akka.cluster.Cluster
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import akka.cluster.pubsub.DistributedPubSubMediator.SubscribeAck
import akka.actor.Props
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.cluster.pubsub.DistributedPubSubMediator.Publish

class Subscriber extends Actor with ActorLogging {

  val mediator = DistributedPubSub(context.system).mediator
  mediator ! Subscribe("content-topic", self)

  def receive = {
    case s: String =>
      log.info("Got {}", s)
    case SubscribeAck(Subscribe("content-topic", None, `self`)) =>
      log.info("subscribing")
  }
}

class Publisher extends Actor {
  import DistributedPubSubMediator.Publish
  val mediator = DistributedPubSub(context.system).mediator

  def receive = {
    case in: String =>
      val out = in.toUpperCase
      mediator ! Publish("content-topic", out)
  }
}

object PubSubApp extends App {

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

  val sub1 = system1.actorOf(Props[Subscriber], "subscriber1")

  val sub2 = system2.actorOf(Props[Subscriber], "subscriber2")
  val sub3 = system2.actorOf(Props[Subscriber], "subscriber3")
  
  val publisher = system3.actorOf(Props[Publisher], "publisher")

  // after a while the subscriptions are replicated
  Thread.sleep(6000)
  
  publisher ! "hello"
  
}