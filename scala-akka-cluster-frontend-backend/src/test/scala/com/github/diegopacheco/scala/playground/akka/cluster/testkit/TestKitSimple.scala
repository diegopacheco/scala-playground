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
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import akka.testkit.TestActorRef
import scala.concurrent.duration._
import scala.concurrent.Await
import akka.pattern.ask
import org.junit.AfterClass
import org.junit.Test

class MyActor extends Actor {
  def receive = {
    case "Say42" => sender() ! 42
  }
}

//@RunWith(classOf[JUnitRunner])
class TestKitSimple(_system: ActorSystem) extends TestKit(_system)
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))
  
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  def testBasicActorCall() {

    implicit val timeout: Timeout = 5 second
    implicit val ec = ExecutionContext.Implicits.global

    val actorRef = TestActorRef(new MyActor)
    val future = actorRef ? "Say42"
    val Success(result: Int) = future.value.get

    result should be(42)
    expectMsg(42)
    
    TestKit.shutdownActorSystem(system)
  }

}