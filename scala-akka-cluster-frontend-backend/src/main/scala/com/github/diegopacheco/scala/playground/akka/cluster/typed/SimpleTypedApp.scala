package com.github.diegopacheco.scala.playground.akka.cluster.typed

import akka.typed._
import akka.typed.ScalaDSL._
import akka.typed.AskPattern._
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout

final case class Greet(whom: String, replyTo: ActorRef[Greeted])
final case class Greeted(whom: String)

object HelloWorld {
  val greeter = Static[Greet] { msg =>
    println(s"Hello ${msg.whom}!")
    msg.replyTo ! Greeted(msg.whom)
  }
}

object SimpleTypedApp extends App {

  import HelloWorld._
  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val timeout: Timeout = 5 second

  val system: ActorSystem[Greet] = ActorSystem("hello", Props(greeter))
  val future: Future[Greeted] = system ? (Greet("world", _))

  for {
    greeting <- future.recover { case ex => ex.getMessage }
    done <- { println(s"result: $greeting"); system.terminate() }
  } println("system terminated")

}