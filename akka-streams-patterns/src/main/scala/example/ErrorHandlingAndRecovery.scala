package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.{ NotUsed, Done }
import java.util.concurrent.TimeoutException

object ErrorHandlingAndRecovery extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer(
    ActorMaterializerSettings(sys)
      .withSupervisionStrategy(Supervision.restartingDecider)
  )

  import sys.dispatcher

  Source(1 to 5)
    .fold(0) { case (total, element) =>
      if (element == 3) throw new Exception("I don't like 3")
      else total + element
    }
    .withAttributes(ActorAttributes.supervisionStrategy(Supervision.restartingDecider))
    .runForeach(println)

  def pipeline =
    Source(1 to 5).map {
      case 3 => throw new Exception("three fails")
      case n => n
    }
  pipeline
   .recoverWithRetries(2, { case _ => pipeline.initialDelay(2.seconds) })
   .runForeach(println)

}
