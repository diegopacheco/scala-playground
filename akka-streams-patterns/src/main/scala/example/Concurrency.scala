package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.{ NotUsed, Done }

object Concurrency extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  def stage(name: String): Flow[Int, Int, NotUsed] =
    Flow[Int].map { index =>
      println(s"Stage $name processing $index by ${Thread.currentThread().getName}")
      index
    }

  /*
  Source(1 to 1000000)
   .via(stage("A"))
   .via(stage("B"))
   .via(stage("C"))
   .runWith(Sink.ignore)
  */

   Source(1 to 1000000)
     .via(stage("A")).async
     .via(stage("B")).async
     .via(stage("C")).async
     .runWith(Sink.ignore)

}
