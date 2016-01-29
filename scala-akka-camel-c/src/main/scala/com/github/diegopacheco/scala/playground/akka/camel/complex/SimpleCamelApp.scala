package com.github.diegopacheco.scala.playground.akka.camel.complex

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import org.apache.camel.builder.RouteBuilder
import org.slf4j.LoggerFactory

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.camel.CamelExtension
import akka.camel.CamelMessage
import akka.camel.Consumer
import akka.camel.Producer
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout

class ProducingWorker(val endpoint: String) extends Actor with Producer {
  override def endpointUri:String = endpoint
}

class ConsumingWorker(val endpoint: String) extends Actor with Consumer {
    private val log = Logging.getLogger(context.system, this)

    override def endpointUri:String = endpoint
    
    override def receive: Receive = {
        case CamelMessage(body, headers) =>
            log.info("Completed round for {} with {}.", endpoint, body)
            sender() ! CamelMessage("Consumed!", headers)
        case _ =>
    }
}

object Ask {
  
   import scala.concurrent.duration._
   import akka.util.Timeout
   import akka.pattern.ask
   implicit val timeout = Timeout(5 seconds) 
      
   def get[T](actor:ActorRef,message:Any):T = {
      val future = actor ? message
      val result:T = Await.result(future, timeout.duration).asInstanceOf[T]
      return result
   }
  
}

object SimpleCamelApp extends App {

  implicit val system = ActorSystem.create("CamelSystem")
  implicit val log = LoggerFactory.getLogger(classOf[App])
      
  CamelExtension(system).context.addRoutes(new RouteBuilder() {
      override def configure() {
          from("direct-vm://input").to("direct-vm://output")
      }
  })
  
  val consumer = system.actorOf(Props(classOf[ConsumingWorker], "direct-vm://output"))
  val producer = system.actorOf(Props(classOf[ProducingWorker], "direct-vm://input"))
  
  println("Calling Camel: " + Ask.get[Any](producer,"Go"))

}