package com.github.diegopacheco.scala.playground.vertx

import io.vertx.lang.scala.VertxExecutionContext
import io.vertx.scala.core._
import io.vertx.scala.ext.web._
import scala.util.{Failure, Success}

object VertXApp extends App {

  val vertx = Vertx.vertx()
  val router = Router.router(vertx)
  router.get("/").handler( req => req.response().end("hello world"))

  implicit val ec = VertxExecutionContext(vertx.getOrCreateContext())

  vertx
    .createHttpServer()
    .requestHandler(router)
    .listenFuture(8080)
    .onComplete {
      case Success(_) => println("Started on 8080")
      case Failure(exception) => println("Failure")
    }

}
