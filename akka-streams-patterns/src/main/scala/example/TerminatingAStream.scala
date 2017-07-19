package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.{ NotUsed, Done }

object TerminatingAStream extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  import sys.dispatcher
  Source
   .single(1)
   .runWith(Sink.ignore) // returns a Future[Done]
   .onComplete(_ => sys.terminate()) // onComplete callback of the future

}
