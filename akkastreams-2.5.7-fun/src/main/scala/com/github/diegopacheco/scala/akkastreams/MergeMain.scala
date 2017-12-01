package com.github.diegopacheco.scala.akkastreams


object MergeMain extends App {
  
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
  
  def toImmutable[A](elements: Iterable[A]) =
  new scala.collection.immutable.Iterable[A] {
    override def iterator: Iterator[A] = elements.toIterator
  }
  
  val stream:Future[Done] = 
    Source.fromFuture( Future.successful(Array.range(0,1001).toSeq)  )
      .mapConcat(toImmutable)
      .flatMapMerge(1, o => Source.fromFuture(Future.successful(o)) )
      .runWith(Sink.foreach(println _))(materializer)
  
   stream.onComplete(_ => system.terminate())
  
}