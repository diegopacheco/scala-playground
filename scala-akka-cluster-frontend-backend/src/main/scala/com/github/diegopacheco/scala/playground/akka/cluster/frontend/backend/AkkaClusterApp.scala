package com.github.diegopacheco.scala.playground.akka.cluster.frontend.backend

import com.typesafe.config.ConfigFactory
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.routing.FromConfig
import akka.routing.{ ActorRefRoutee, RoundRobinRoutingLogic, Router }
import akka.actor.ActorRef
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.UnreachableMember
import akka.cluster.ClusterEvent.MemberEvent
import akka.cluster.ClusterEvent.MemberUp
import akka.routing.RoundRobinPool

trait Operation
case class Sum(x:Int,y:Int) extends Operation
case class Result(value:Int)

class FrontendActor extends Actor {
   
  val cluster = Cluster(context.system)
  cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])
  
   var router = {
     val routees = Vector.fill(2) { 
        val backendActor = context.actorOf(Props[BackendActor])
        context watch backendActor
        ActorRefRoutee(backendActor)
     }
     Router(RoundRobinRoutingLogic(), routees)
   }
  
  
   def receive = {
     case s:Sum => {
        println("FrontEndactor got it " + s)
        router.route(s, sender())
     }
     case r:Result => {
        println("Now the Frontend got a response from the backend: " + r)
     }
     
     case u:MemberUp => println("FrontendActor got notification from Cluster : " + u.member)
   }
  
}

class BackendActor extends Actor {
    def receive = {
       case s:Sum => {
           println("BackendActor got it " + s + ". I'm " + self.path)
           val r:Result = Result(s.x + s.y)
           println("Result: " + r)
           sender() ! r      
       }
   }
}

object AkkaClusterApp extends App {
  
  def bootup(port:String):ActorSystem = {
      val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.load())
      val system = ActorSystem("ClusterSystem", config)
      system
  }
  
  val node1  = bootup("2551")
  val node2  = bootup("2552")
  val node3  = bootup("0")
  
  val frontend = node1.actorOf(Props[FrontendActor], name = "FrontendActor")
  
  for (i <- 1 to 10){
    val r = Ask.get[Result](frontend, Sum(1,i))
    println("Outside of teh cluster, i just got it: " + r)
  }
  
   val router2:ActorRef = node2.actorOf(RoundRobinPool(5).props(Props[FrontendActor]), "FrontendActorRouterRoundRobin")
   println("Router on Frontend: " + router2)
   
   for (i <- 1 to 3){
    val r = Ask.get[Result](router2, Sum(1,i))
    println("Outside of teh cluster, i just got it: " + r)
  }

    
}