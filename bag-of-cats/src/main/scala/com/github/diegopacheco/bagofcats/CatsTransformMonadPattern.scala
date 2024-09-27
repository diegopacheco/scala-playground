package com.github.diegopacheco.bagofcats

import cats.data.EitherT
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object CatsTransformMonadPattern extends App{

  implicit val ec: ExecutionContext = ExecutionContext.global

  sealed abstract class HttpError
  final case class NotFound(item: String) extends HttpError
  final case class BadRequest(msg: String) extends HttpError

  type FutureEither[A] = EitherT[Future, HttpError, A]

  def findItem(id: String): FutureEither[String] = {
    if (id == "1") EitherT.right(Future.successful("Item 1"))
    else EitherT.left(Future.successful(NotFound(id)))
  }

  def validateItem(item: String): FutureEither[String] = {
    if (item == "Item 1") EitherT.right(Future.successful(item))
    else EitherT.left(Future.successful(BadRequest("Item not found")))
  }

  def processItem(id: String): FutureEither[String] = {
    for {
      item <- findItem(id)
      validItem <- validateItem(item)
    } yield validItem
  }

  val result = processItem("1")
  result.value.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(s"Failed with exception: $exception")
  }

}
