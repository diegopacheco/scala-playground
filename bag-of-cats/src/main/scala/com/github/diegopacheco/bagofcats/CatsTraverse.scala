package com.github.diegopacheco.bagofcats

import cats.Traverse
import cats.instances.future.*
import cats.instances.list.*
import cats.syntax.traverse.*

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}

object CatsTraverse extends App{

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.global

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60)

  hostnames.traverse(getUptime).foreach(println)

}
