package com.github.diegopacheco.scala.playground.reactive.streams

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.actor.ActorSystem

object HttpApp extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val route =
    path("hello") {
      get {
        complete {
          "<h1>Say hello to akka-http</h1>"
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  Console.readLine() 
  bindingFuture
    .flatMap(_.unbind()) 
    .onComplete(_ => system.shutdown())
    
}

