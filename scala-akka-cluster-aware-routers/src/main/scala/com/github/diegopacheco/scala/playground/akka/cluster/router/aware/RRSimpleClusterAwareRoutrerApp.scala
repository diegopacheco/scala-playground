package com.github.diegopacheco.scala.playground.akka.cluster.router.aware

import akka.actor.Actor
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.routing.FromConfig
import akka.actor.Props
import akka.cluster.Cluster
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope

class RRActorPrinter extends Actor {
  def receive = {
    case s:Any =>
      println("Print: " + s + " - " + self.path + " - " + context.system.settings.config.getString("akka.remote.netty.tcp.port"))
  }
}

object RRSimpleClusterAwareRoutrerApp extends App {
  
  def bootup(port: String, role: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"akka.cluster.roles = [$role]"))
      .withFallback(ConfigFactory.parseString(s"""
                        akka.actor.deployment {
                          /router1 {
                            router = consistent-hashing-pool
                            cluster {
                              enabled = on
                              max-nr-of-instances-per-node = 3
                              allow-local-routees = on
                              use-role = compute
                            }                          
                          }
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
  
  val router1: ActorRef = Cluster(system1).system.actorOf(FromConfig.props(Props[RRActorPrinter]), "router1")
  Thread.sleep(5000)
  
  for (i <- 1 to 200) router1 ! new ConsistentHashableEnvelope(s"Hi there? $i ", i)
  
}