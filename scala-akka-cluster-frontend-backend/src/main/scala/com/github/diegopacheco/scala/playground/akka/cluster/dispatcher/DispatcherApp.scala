package com.github.diegopacheco.scala.playground.akka.cluster.dispatcher

import akka.actor.Actor
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.actor.Props

class SimplePrinterActor extends Actor {
  def receive = {
    case s: String => 
      println("Print: " + s + " - " + self.path + " - " + context.dispatcher)
  }
}

object DispatcherApp extends App {

  def bootup(port:String, dispatcher:String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
                    .withFallback(ConfigFactory.parseString(s"""
                              my-dispatcher {
                                  type = Dispatcher
                                  executor = "fork-join-executor"
                                  fork-join-executor {
                                    parallelism-min = 2
                                    parallelism-factor = 2.0
                                    parallelism-max = 5
                                  }
                                  throughput = 20
                                }
                    """)).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val system = bootup("2551","compute")
  implicit val executionContext = system.dispatchers.lookup("my-dispatcher")
  
  val printer = system.actorOf(Props[SimplePrinterActor], "printer")

  for(i <- 1 to 200) printer ! s"Hi there? $i "
  
}