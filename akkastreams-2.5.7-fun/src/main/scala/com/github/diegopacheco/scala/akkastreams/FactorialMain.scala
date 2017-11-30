package com.github.diegopacheco.scala.akkastreams

object FactorialMain extends App {
  
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

  val result: Future[IOResult] =
     factorials
       .map(num ⇒ ByteString(s"$num\n"))
       .runWith(FileIO.toPath(Paths.get("factorials.txt")))
  
}