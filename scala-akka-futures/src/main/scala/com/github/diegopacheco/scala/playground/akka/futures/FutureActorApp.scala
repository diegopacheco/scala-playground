package com.github.diegopacheco.scala.playground.akka.futures

import akka.actor.ActorSystem
import akka.actor.Actor
import scala.concurrent.Await
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import akka.pattern.ask
import scala.concurrent.{ ExecutionContext, Promise }
import akka.actor.Props
import akka.pattern.pipe

class TheGoodActor extends Actor {
  def receive = {
    case n: Any =>
      val result = n + "Is Good"
      println(result)
      sender() ! result
  }
}

object FutureActorApp extends App {
  
  val system = ActorSystem("futures-system")
  implicit val ec = system.dispatcher
  implicit val timeout:Timeout = 5 seconds

  val actor =system.actorOf(Props[TheGoodActor], name = "TheGoodActor")  
  
  val future:Future[String] = ask(actor, "Scala ").mapTo[String]
  
  val result = Await.result(future, 3 seconds).asInstanceOf[String]
  println("Actor Future Result: " + result)
  
  val f = Future { "Hi Whatz Sup? " } pipeTo actor
  val r = Await.result(f, 3 seconds).asInstanceOf[String]
  println("Actor Future Result: " + r)
  
}