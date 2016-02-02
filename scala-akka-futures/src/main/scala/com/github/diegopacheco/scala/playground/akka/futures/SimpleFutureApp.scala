package com.github.diegopacheco.scala.playground.akka.futures

import akka.actor.ActorSystem

object SimpleFutureApp extends App {

  import scala.concurrent.{ ExecutionContext, Promise }
  val system = ActorSystem("futures-system") 
  implicit val ec = system.dispatcher
  
  val f = Promise.successful("foo").future
  println(f)  
  println(f.isCompleted)
  
  import scala.concurrent.Await
  import akka.util.Timeout
  import scala.concurrent.duration._
  
  val result = Await.result(f, 3 seconds).asInstanceOf[String]
  println(result)
  
}