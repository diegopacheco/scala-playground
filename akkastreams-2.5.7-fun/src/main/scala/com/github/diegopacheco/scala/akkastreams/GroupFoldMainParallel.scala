package com.github.diegopacheco.scala.akkastreams

object GroupFoldMainParallel extends App {
  
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
  
  def process(acc:Int,i:Array[Int]):Int = {
     val total = i.foldLeft(0)(_+_);
     i.foreach( j => print(" " + j))
     println(" Total => " + total)
     total + acc
  }
  
  val stream:Future[Done] = 
    Source(List( Array.range(0,11) , Array.range(11,22) , Array.range(22,32) ))
      .grouped(3)  
      .mapConcat(identity)
      .fold(0)(process _).async
      .runWith(Sink.foreach(println _))(materializer)
  
   stream.onComplete(_ => system.terminate())
      
}