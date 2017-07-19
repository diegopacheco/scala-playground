package example

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import scala.concurrent.Future

object FlatteningAStream extends App {

  implicit val sys = ActorSystem("akka-stream-patterns")
  implicit val mat = ActorMaterializer()

  Source('A' to 'E')
    .mapConcat(letter => (1 to 3).map(index => s"$letter$index"))
    .runForeach(println)

  Source
    .fromFuture(Future.successful(1 to 10))
    .mapConcat(identity)
    .runForeach(println)

  def toImmutable[A](elements: Iterable[A]) =
      new scala.collection.immutable.Iterable[A] {
        override def iterator: Iterator[A] = elements.toIterator
  }
  Source
    .fromFuture(Future.successful(Stream.range(1, 10)))
    .mapConcat(toImmutable)
    .runForeach(println)

  Source
    .fromFuture(Future.successful(Stream.range(1, 10)))
    .flatMapConcat(Source.apply)
    .runForeach(println)

}
