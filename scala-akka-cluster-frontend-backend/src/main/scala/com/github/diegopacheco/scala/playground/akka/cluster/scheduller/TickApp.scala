package com.github.diegopacheco.scala.playground.akka.cluster.scheduller

import akka.actor.Actor
import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import akka.cluster.Cluster

class ScheduleInConstructor extends Actor {

  import context.dispatcher
  val tick = context.system.scheduler.schedule(500 millis, 1000 millis, self, "tick")

  override def postStop() = tick.cancel()

  def receive = {
    case "tick" => println("tick " + System.currentTimeMillis())
  }
}

object TickApp extends App {

  def bootup(port: String): ActorSystem = {
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val node1 = bootup("2551")
  val node2 = bootup("2552")
  val node3 = bootup("0")

  Thread.sleep(6000)
  node1.actorOf(Props[ScheduleInConstructor], name = "TickActor")

}