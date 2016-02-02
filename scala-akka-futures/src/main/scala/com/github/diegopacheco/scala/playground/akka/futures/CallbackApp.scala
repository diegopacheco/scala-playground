package com.github.diegopacheco.scala.playground.akka.futures

import akka.actor.ActorSystem
import scala.util.Failure
import scala.util.Success

object CallbackApp extends App{
  
  import scala.concurrent.{ ExecutionContext, Promise }
  val system = ActorSystem("futures-system") 
  implicit val ec = system.dispatcher
  
  val f = Promise.successful("foo").future
  
  f.onComplete {
    case Success(result) => println(" :) " + result)  
    case Failure(error) => println(" :( " + error)  
  }
  
}