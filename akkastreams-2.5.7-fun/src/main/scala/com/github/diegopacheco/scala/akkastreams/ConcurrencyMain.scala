package com.github.diegopacheco.scala.akkastreams

import akka.NotUsed

object ConcurrencyMain extends App {
   
  import akka.Done
  import akka.actor.ActorSystem
  import akka.stream._
  import akka.stream.scaladsl._
  import scala.concurrent._
  
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  
  import scala.concurrent.Future
  import ExecutionContext.Implicits.global
  
  def stage(name: String): Flow[Int, Int, NotUsed] =
  Flow[Int].map { index =>
    println(s"Stage $name processing $index by ${Thread.currentThread().getName}")
    index
  }
 
  val stream:Future[Done] =
      Source(1 to 100000)
        .via(stage("A")).async
        .via(stage("B")).async
        .via(stage("C")).async
        .runWith(Sink.ignore)
     
   stream.onComplete(_ => system.terminate())
        
}