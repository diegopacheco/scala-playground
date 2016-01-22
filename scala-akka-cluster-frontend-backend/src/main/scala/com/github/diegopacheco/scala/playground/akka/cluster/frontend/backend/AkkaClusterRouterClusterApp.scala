package com.github.diegopacheco.scala.playground.akka.cluster.frontend.backend

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.routing.ConsistentHashingGroup
import akka.cluster.routing.ClusterRouterGroupSettings
import akka.cluster.Cluster
import akka.cluster.routing.ClusterRouterGroup
import akka.cluster.routing.ClusterRouterPool
import akka.routing.ConsistentHashingPool
import akka.cluster.routing.ClusterRouterPoolSettings
import akka.actor.Props
import akka.actor.Actor
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope

final case class Entry(key: String, value: String)

class WorkerActor extends Actor {
   def receive = {
     case e:Entry => {
        println("WorkerActor got it " + e + " I`m " + self + " @ " + context.system + ":" + context.system.settings.config.getString("akka.remote.netty.tcp.port"))
     }
   }
}

object AkkaClusterRouterClusterApp extends App {

  def bootup(port: String): ActorSystem = {
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val node1 = bootup("2551")
  val node2 = bootup("2552")
  val node3 = bootup("0")
  
  Cluster(node1)
  Cluster(node2)
  Cluster(node3)

  Thread.sleep(10000)
  
  val workerRouter = node1.actorOf(
    ClusterRouterPool(ConsistentHashingPool(0), ClusterRouterPoolSettings(
      totalInstances = 9, maxInstancesPerNode = 3,
      allowLocalRoutees = false, useRole = None)).props(Props[WorkerActor]),
      name = "workerRouter")
      
  node2.actorOf(
    ClusterRouterPool(ConsistentHashingPool(0), ClusterRouterPoolSettings(
      totalInstances = 9, maxInstancesPerNode = 3,
      allowLocalRoutees = true, useRole = None)).props(Props[WorkerActor]),
      name = "workerRouter")
      
  node3.actorOf(
    ClusterRouterPool(ConsistentHashingPool(0), ClusterRouterPoolSettings(
      totalInstances = 9, maxInstancesPerNode = 3,
      allowLocalRoutees = true, useRole = None)).props(Props[WorkerActor]),
      name = "workerRouter")
  
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "1")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi1", "HELLO1"), hashKey = "0")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi2", "HELLO2"), hashKey = "1")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi3", "HELLO3"), hashKey = "0")
  
}