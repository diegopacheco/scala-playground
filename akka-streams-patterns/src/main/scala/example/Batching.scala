package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._

object Batching extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  Source(1 to 100)
    .grouped(10)
    .runForeach(println)

  Source
    .tick(0.millis, 10.millis, ())
    .groupedWithin(100, 100.millis)
    .map { batch => println(s"Processing batch of ${batch.size} elements"); batch }
    .runWith(Sink.ignore)

}
