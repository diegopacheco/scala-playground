package com.github.diegopacheco.bagofcats

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

/**
 * Functor is anything that can be mapped over(map).
 */
object Functors extends App{

  val result = List(1, 2, 3).
                map(n => n + 1).
                map(n => n * 2).
                map(n => s"${n}!")
  println(result)

  val result2 = Option(123).
                map(n => n + 1).
                map(n => n * 2).
                map(n => s"${n}!")
  println(result2)

  val result3 = Right[String, Int](123).
                map(n => n + 1).
                map(n => n * 2).
                map(n => s"${n}!")
  println(result3)

  implicit val ec: ExecutionContext = ExecutionContext.global
  
  val future: Future[String] =
    Future(123).
      map(n => n + 1).
      map(n => n * 2).
      map(n => s"${n}!")
  println(Await.result(future, 1.second))
}
