package com.github.diegopacheco.scala.playground.akka.cluster.simple

import akka.actor.Actor
import akka.util.Timeout
import akka.actor.ActorRef
import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.SmallestMailboxPool
import akka.routing.SmallestMailboxPool
import akka.pattern.ask
import scala.concurrent.ExecutionContext
import scala.util.Try
import scala.util.Failure
import scala.util.Success

case class AddRequest(x: Int, y: Int)
case class AddResponse(sum: Int)

class Calculator extends Actor {
  def receive = {
    case AddRequest(a, b) => sender() ! AddResponse(a + b)
  }
}

object SystemHelper {

  def compute(calc: ActorRef) {
    implicit val timeout: Timeout = 5 second
    implicit val ec = ExecutionContext.Implicits.global
    
    for (x <- 0 to 5) {
      for (y <- 0 to 5) {
        (calc ? AddRequest(x, y)).onComplete { 
            case Success(result)  => println("%d + %d = %s".format(x, y, result))
            case Failure(failure) => println(failure)
        }
      }
    }
  }

}

object CalcActorApp extends App {

  val system = ActorSystem("MySystem")
  val calc = system.actorOf(Props[Calculator].withRouter(SmallestMailboxPool(8)))
  SystemHelper.compute(calc)
  
}