package com.github.diegopacheco.scala.playground.akka.cluster.testkit.actorref

import akka.testkit.TestKit
import akka.testkit.ImplicitSender
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import akka.testkit.TestActorRef
import akka.actor.Actor
import org.scalatest._

class SimpleActorRefTest extends TestKit(ActorSystem("ClusterSystem"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll {
    shutdown()
  }

  "Actor Should Work Fine" must {
    val actorRef = TestActorRef(new Actor {
      def receive = {
        case "hello" => throw new IllegalArgumentException("boom")
      }
    })
    intercept[IllegalArgumentException] { actorRef.receive("hello") }
    println("All Good!")
  }

}