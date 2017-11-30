package com.github.diegopacheco.scala.akkastreams

object AsyncBoundaryMain extends App {
  
  import akka.stream._
  import akka.stream.scaladsl._
  
  import akka.{ NotUsed, Done }
  import akka.actor.ActorSystem
  import akka.util.ByteString
  import scala.concurrent._
  import scala.concurrent.duration._
  import java.nio.file.Paths
  
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  
  Source(List(1, 2, 3))
  .map(_ + 1).async
  .map(_ * 2)
   .runForeach(i ⇒ println(i))(materializer)
  
}