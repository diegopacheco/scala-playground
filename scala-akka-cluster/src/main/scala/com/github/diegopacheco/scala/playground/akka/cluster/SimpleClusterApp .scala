package com.github.diegopacheco.scala.playground.akka.cluster

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.Props


object SimpleClusterApp extends App {
 
    if (args.isEmpty)
      startup(Seq("2551", "2552", "0"))
    else
      startup(args)
 
  def startup(ports: Seq[String]): Unit = {
    ports foreach { port =>

      // Override the configuration of the port
      val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.load())

      // Create an Akka system
      val system = ActorSystem("ClusterSystem", config)

      // Create an actor that handles cluster domain events
      system.actorOf(Props[SimpleClusterListener], name = "clusterListener")
      
    }
  }

}