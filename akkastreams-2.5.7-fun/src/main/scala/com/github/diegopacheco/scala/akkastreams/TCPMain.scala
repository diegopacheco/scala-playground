package com.github.diegopacheco.scala.akkastreams

import akka.stream.scaladsl.Tcp.ServerBinding
import akka.stream.scaladsl.Tcp.IncomingConnection

object TCPMain extends App {

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
  
  import akka.stream.scaladsl.Framing

  val connections: Source[ IncomingConnection, Future[ServerBinding]] = Tcp().bind("127.0.0.1", 8888)
  
    connections runForeach { connection=> println(s"New connection from: ${connection.remoteAddress}") 
  
    val echo = Flow[ByteString]
      .via(Framing.delimiter(
        ByteString("\n"),
        maximumFrameLength = 256,
        allowTruncation = true))
      .map(_.utf8String)
      .map(_ + "!!!\n")
      .map(ByteString(_))
  
    connection.handleWith(echo)
    
  }

}