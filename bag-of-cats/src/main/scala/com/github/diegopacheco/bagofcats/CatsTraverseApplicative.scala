package com.github.diegopacheco.bagofcats

import cats.Applicative
import cats.instances.future.*
import cats.syntax.applicative.*
import cats.syntax.apply.*

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.DurationInt // for mapN

object CatsTraverseApplicative extends App{

  implicit val ec:ExecutionContext =
    ExecutionContext.global

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60) // just for demonstration

  def oldCombine(accum: Future[List[Int]],
                 host: String
                ): Future[List[Int]] = {
    val uptime = getUptime(host)
    for {
      accum  <- accum
      uptime <- uptime
    } yield accum :+ uptime
  }

  def newCombine(accum: Future[List[Int]],
                 host: String): Future[List[Int]] =
    (accum, getUptime(host)).mapN(_ :+ _)

  def listTraverse[F[_] : Applicative, A, B]
    (list: List[A])(func: A => F[B]): F[List[B]] =
    list.foldLeft(List.empty[B].pure[F]) { (accum, item) =>
      (accum, func(item)).mapN(_ :+ _)
    }

  def listSequence[F[_] : Applicative, B]
  (list: List[F[B]]): F[List[B]] =
    listTraverse(list)(identity)

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  val totalUptime = listTraverse(hostnames)(getUptime)
  totalUptime.onComplete(println)

}
