package com.github.diegopacheco.scala.playground.reactive.streams

import scala.concurrent.ExecutionContext
import scala.actors.threadpool.AtomicInteger
import scala.concurrent.Future
import akka.stream.ActorMaterializerSettings
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.actor.ActorSystem

class SometimesSlowService(implicit ec: ExecutionContext) {

  private val runningCount = new AtomicInteger

  def convert(s: String): Future[String] = {
    println(s"running: $s (${runningCount.incrementAndGet()})")
    Future {
      if (s.nonEmpty && s.head.isLower)
        Thread.sleep(500)
      else
        Thread.sleep(20)
      println(s"completed: $s (${runningCount.decrementAndGet()})")
      s.toUpperCase
    }
  }
}

object ReactiveSlow extends App {
  
  implicit val system = ActorSystem("reactive-actors")
  
  implicit val blockingExecutionContext = system.dispatcher
  val service = new SometimesSlowService

  implicit val materializer = ActorMaterializer(
    ActorMaterializerSettings(system).withInputBuffer(initialSize = 4, maxSize = 4))

  Source(List("a", "B", "C", "D", "e", "F", "g", "H", "i", "J"))
    .map(elem => { println(s"before: $elem"); elem })
    .mapAsync(4)(service.convert)
    .runForeach(elem => println(s"after: $elem"))

}