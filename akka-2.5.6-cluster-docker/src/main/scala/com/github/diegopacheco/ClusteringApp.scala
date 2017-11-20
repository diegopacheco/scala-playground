package com.github.diegopacheco

import akka.actor.ActorSystem
import akka.actor.Props

object ClusteringApp extends App {
   import com.github.diegopacheco.ClusteringConfig._
   implicit val system = ActorSystem(clusterName)
   val clusterListener = system.actorOf(Props[ClusterListener], name = "clusterListener")
}