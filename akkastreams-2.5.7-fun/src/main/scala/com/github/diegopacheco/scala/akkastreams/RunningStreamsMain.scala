package com.github.diegopacheco.scala.akkastreams

object RunningStreamsMain extends App {
  
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
  
  val source = Source(1 to 10)
  val sink = Sink.fold[Int, Int](0)(_ + _)
  val sum: Future[Int] = source.runWith(sink)
  
  sum.foreach(println(_));
  
}