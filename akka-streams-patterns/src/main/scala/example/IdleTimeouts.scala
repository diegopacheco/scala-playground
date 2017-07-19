package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.{ NotUsed, Done }
import java.util.concurrent.TimeoutException

object IdleTimeouts extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  import sys.dispatcher

  Source
    .tick(0.millis, 1.minute, ())
    .idleTimeout(30.seconds)
    .runWith(Sink.ignore)
    .recover {
      case _:TimeoutException =>
        println("No messages received for 30 seconds")
    }

}
