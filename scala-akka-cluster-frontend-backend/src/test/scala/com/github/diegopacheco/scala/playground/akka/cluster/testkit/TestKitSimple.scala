package com.github.diegopacheco.scala.playground.akka.cluster.testkit

import scala.util.Success
import akka.actor.Actor
import akka.testkit.TestKit
import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import akka.util.Timeout
import akka.testkit.ImplicitSender
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import org.scalatest.WordSpecLike
import org.scalatest.FunSuite
import akka.testkit.TestActorRef
import scala.concurrent.duration._
import scala.concurrent.Await
import akka.pattern.ask
import org.junit.AfterClass
import org.junit.Test
import scala.concurrent.duration._
import akka.cluster.Cluster

class MyActor extends Actor {
  def receive = {
    case "Say42" => sender() ! 42
  }
}

class TestKitSimple extends TestKit(ActorSystem("ClusterSystem"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {
  
  override def afterAll {
    shutdown()
  }
 
  "An MyActor actor" must {
 
    "send back messages 42 as anwser" in {
        implicit val timeout: Timeout = 5 second
        implicit val ec = ExecutionContext.Implicits.global
    
        val actorRef = TestActorRef(new MyActor)
        val future = actorRef ? "Say42"
        val Success(result: Int) = future.value.get
        
        //expectMsg(42)    
        result should be(42)
    }
  }

}