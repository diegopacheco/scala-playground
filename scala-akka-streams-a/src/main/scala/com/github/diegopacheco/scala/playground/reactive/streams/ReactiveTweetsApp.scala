package com.github.diegopacheco.scala.playground.reactive.streams

import akka.stream.ActorMaterializer
import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.scaladsl.Sink
import scala.concurrent.duration._
import akka.stream.scaladsl._
import akka.stream._
import scala.concurrent.Future

final case class Author(handle: String)

final case class Hashtag(name: String)

final case class Tweet(author: Author, timestamp: Long, body: String) {
  def hashtags: Set[Hashtag] =
    body.split(" ").collect { case t if t.startsWith("#") => Hashtag(t) }.toSet
}

object ReactiveTweetsApp extends App {

  implicit val system = ActorSystem("reactive-tweets")
  implicit val ec = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val akka = Hashtag("#akka")

  val tweets: Source[Tweet, Unit] = Source(Vector(Tweet(Author("diego_pacheco"), 0, "#akka stream is cool"),
                                                  Tweet(Author("diego_pacheco"), 1, "OMG GitHub is Down"),
                                                  Tweet(Author("diego_pacheco"), 2, "Stream is so HOT and awesome")
                                    ))

  val authors: Source[Author, Unit] =
    tweets
      .filter(_.hashtags.contains(akka))
      .map(_.author)

  authors.runWith(Sink.foreach(println))
  
  //
  // Graph
  //
  
  val count: Flow[Tweet, Int, Unit] = Flow[Tweet].map(_ => 1)
  val sumSink: Sink[Int, Future[Int]] = Sink.fold[Int, Int](0)(_ + _)
  val counterGraph: RunnableGraph[Future[Int]] =
    tweets
      .via(count)
      .toMat(sumSink)(Keep.right)
  val sum: Future[Int] = counterGraph.run()
  sum.foreach(c => println(s"Total tweets processed: $c"))
  
  //
  // Play with Source
  //

  Source((1 to 10).toVector).runForeach { n => println(s"$n @ ${System.currentTimeMillis}") }

}