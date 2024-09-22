package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.instances.future._ // for Monad
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

object CatsMonadFutureApp extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val fm = Monad[Future]
  val futurem = fm.flatMap(Monad[Future].pure(1))(x => Monad[Future].pure(x + 2))
  val res1 = Await.result(futurem, 10.seconds)
  println(res1)
}