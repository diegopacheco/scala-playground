package com.github.diegopacheco.scala.playground.akka.cluster.testkit.probes

import akka.actor.Actor
import akka.actor.ActorRef
import akka.testkit.TestKit
import akka.testkit.TestProbe
import akka.testkit.ImplicitSender
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import akka.actor.Props
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import scala.concurrent.duration._

class MyDoubleEcho extends Actor {
  var dest1: ActorRef = _
  var dest2: ActorRef = _

  def receive = {
    case (d1: ActorRef, d2: ActorRef) =>
      dest1 = d1
      dest2 = d2
    case x =>
      dest1 ! x
      dest2 ! x
  }
}

class ProbesTest extends TestKit(ActorSystem("ClusterSystem"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll {
    shutdown()
  }

  "An MyDoubleEcho actor" must {

    "send back messages to 2 actors" in {
      val probe1 = TestProbe()
      val probe2 = TestProbe()
      val actor = system.actorOf(Props[MyDoubleEcho])
      actor ! ((probe1.ref, probe2.ref))
      actor ! "hello"
      probe1.expectMsg(500 millis, "hello")
      probe2.expectMsg(500 millis, "hello")
    }
  }

}