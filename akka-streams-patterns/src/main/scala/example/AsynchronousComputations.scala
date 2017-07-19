package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._

object AsynchronousComputations extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  import sys.dispatcher

  def writeBatchToDatabase(batch: Seq[Int]): Future[Unit] =
    Future {
      println(s"Writing batch of $batch to database by ${Thread.currentThread().getName}")
    }

  Source(1 to 1000000)
    .grouped(10)
    .mapAsync(10)(writeBatchToDatabase)
    .runWith(Sink.ignore)
}
