package com.github.diegopacheco.scala.playground.akka.cluster.frontend.backend

import com.typesafe.config.ConfigFactory
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.cluster.Cluster
import akka.cluster.routing.ClusterRouterPool
import akka.cluster.routing.ClusterRouterPoolSettings
import akka.routing.ConsistentHashingPool
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope
import akka.actor.ActorRef
import akka.routing.ConsistentHashingGroup

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
  
  def deployActor(system:ActorSystem,props:Props,name:String):ActorRef = {
     val actor = system.actorOf(props,name)
     println("Actor Deployed " + actor.path + "@" + system + ":" + system.settings.config.getString("akka.remote.netty.tcp.port"))
     actor
  }
  
  val node1 = bootup("2551")
  val node2 = bootup("2552")
  val node3 = bootup("2553")
  
  Cluster(node1)
  Cluster(node2)
  Cluster(node3)

  Thread.sleep(10000)
  
  val workerRouter = node1.actorOf(
    ClusterRouterPool(ConsistentHashingPool(0), ClusterRouterPoolSettings(
      totalInstances = 9, maxInstancesPerNode = 3,
      allowLocalRoutees = true, useRole = None)).props(Props[WorkerActor]),
      name = "workerRouter")
  
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "1")
  workerRouter ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  
  deployActor(node2, Props[WorkerActor], "w1")
  deployActor(node2, Props[WorkerActor], "w2")
  deployActor(node2, Props[WorkerActor], "w3")
  
  val paths = List("/user/w1", "/user/w2", "/user/w3")
  val routerGroup: ActorRef = node2.actorOf(ConsistentHashingGroup(paths).props(), "routerGroup")

  routerGroup ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  routerGroup ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "1")
  routerGroup ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "0")
  routerGroup ! ConsistentHashableEnvelope(message = Entry("hi", "HELLO"), hashKey = "2")
    
}
