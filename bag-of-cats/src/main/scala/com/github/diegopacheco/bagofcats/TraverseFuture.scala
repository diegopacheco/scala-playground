package com.github.diegopacheco.bagofcats

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.DurationInt

object TraverseFuture extends App {

  implicit val ec: ExecutionContext = ExecutionContext.global

  val hostnames = List(
    "google.com",
    "apple.com",
    "terra.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60) // just for demonstration

  val allUptimes: Future[List[Int]] =
    hostnames.foldLeft(Future(List.empty[Int])) {
      (accum, host) =>
        val uptime = getUptime(host)
        for {
          accum <- accum
          uptime <- uptime
        } yield accum :+ uptime
    }
  allUptimes.onComplete(println)

  val allUptimes2: Future[List[Int]] =
    Future.traverse(hostnames)(getUptime)
  allUptimes2.onComplete(println)
}
