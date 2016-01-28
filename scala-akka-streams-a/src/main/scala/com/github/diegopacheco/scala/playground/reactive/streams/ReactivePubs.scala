package com.github.diegopacheco.scala.playground.reactive.streams

import scala.annotation.tailrec
import akka.actor.Props
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Flow
import akka.stream.scaladsl.Source
import akka.stream.ActorMaterializer
import akka.actor.ActorSystem

object JobManager {
  def props: Props = Props[JobManager]

  final case class Job(payload: String)
  case object JobAccepted
  case object JobDenied
}

class JobManager extends ActorPublisher[JobManager.Job] {
  import akka.stream.actor.ActorPublisherMessage._
  import JobManager._

  val MaxBufferSize = 2
  var buf = Vector.empty[Job]

  def receive = {
    case job: Job if buf.size == MaxBufferSize =>
      sender() ! JobDenied
    case job: Job =>
      sender() ! JobAccepted
      if (buf.isEmpty && totalDemand > 0)
        onNext(job)
      else {
        buf :+= job
        deliverBuf()
      }
    case Request(_) =>
      deliverBuf()
    case Cancel =>
      context.stop(self)
  }

  @tailrec final def deliverBuf(): Unit =
    if (totalDemand > 0) {
      if (totalDemand <= Int.MaxValue) {
        val (use, keep) = buf.splitAt(totalDemand.toInt)
        buf = keep
        use foreach onNext
      } else {
        val (use, keep) = buf.splitAt(Int.MaxValue)
        buf = keep
        use foreach onNext
        deliverBuf()
      }
    }
}

import akka.routing.GetRoutees
import akka.routing.Routees
import scala.concurrent.Await
import akka.actor.Actor
import akka.actor.ActorRef

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

object ReactiveActors2 extends App {
  
  implicit val system = ActorSystem("reactive-actors")
  implicit val materializer = ActorMaterializer()
  
  val jobManagerSource = Source.actorPublisher[JobManager.Job](JobManager.props)
  
  val ref = Flow[JobManager.Job]
    .map(_.payload.toUpperCase)
    .map { elem => println(elem); elem }
    .to(Sink.ignore)
    .runWith(jobManagerSource)
  
  ref ! JobManager.Job("x") // Will go DLQ since no boddy to receive
    
  println("Result: " + Ask.get[Any](ref, JobManager.Job("a")))
  println("Result: " + Ask.get[Any](ref, JobManager.Job("b")))
  println("Result: " + Ask.get[Any](ref, JobManager.Job("c")))
  println("Result: " + Ask.get[Any](ref, JobManager.Job("d")))
  
}