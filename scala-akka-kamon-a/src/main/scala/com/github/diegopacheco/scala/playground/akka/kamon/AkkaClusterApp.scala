package com.github.diegopacheco.scala.playground.akka.kamon

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.cluster.Cluster
import java.lang.management.ManagementFactory
import com.typesafe.config.ConfigValueFactory
import com.typesafe.config.ConfigResolveOptions
import com.typesafe.config.Config
import com.typesafe.config._
import kamon.Kamon
import akka.actor.Actor
import akka.actor.Props

class PongActor extends Actor {
  def receive = {
    case a: Any =>
      Thread.sleep(3000)
      sender() ! "Pong"
  }
}

class PingActor extends Actor {
  
  val pong = context.actorOf(Props[PongActor], name = "Ponger")
  
  def receive = {
    case "start" =>
      pong.tell("Ping", self)
    case a: Any =>
      Thread.sleep(3000)
      sender() ! "Ping"
  }
}

// -javaagent:C:\\Users\\diego\\.ivy2\\cache\\org.aspectj\\aspectjweaver\\jars\\aspectjweaver-1.8.2.jar
object AkkaClusterApp extends App {

  Kamon.start(ConfigFactory.load())

  def bootup(port: String, role: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"akka.cluster.roles = [$role]"))
      .withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val runtimeMxBean = ManagementFactory.getRuntimeMXBean()
  println(runtimeMxBean.getInputArguments())

  val node1 = bootup("2551", "compute")
  val node2 = bootup("2552", "compute")
  val node3 = bootup("0", "compute")

  Cluster(node1)
  Cluster(node2)
  Cluster(node3)
  
  Thread.sleep(6000)
  
  node1.actorOf(Props[PingActor],"PingPong1") ! "start"
  node2.actorOf(Props[PingActor],"PingPong2") ! "start"
  node3.actorOf(Props[PingActor],"PingPong3") ! "start"

}