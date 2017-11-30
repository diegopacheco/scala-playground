package com.github.diegopacheco.scala.akkastreams

object TimeBasedProcessingMain extends App {
 
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
  
  val source: Source[Int, NotUsed] = Source(1 to 100)
  
  val factorials = source.scan(BigInt(1))((acc, next) ⇒ acc * next)
  
  factorials
    .zipWith(Source(0 to 100))((num, idx) ⇒ s"$idx! = $num")
    .throttle(1, 1.second, 1, ThrottleMode.shaping)
    .runForeach(println)
  
}