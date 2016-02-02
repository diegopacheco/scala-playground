package com.github.diegopacheco.scala.playground.akka.futures

import scala.concurrent.Future
import akka.actor.ActorSystem

object ForApp extends App {
  
  val system = ActorSystem("futures-system")
  implicit val ec = system.dispatcher
  
  val f = for {
    a <- Future(10 / 2)
    b <- Future(a + 1)
    c <- Future(a - 1)
    if c > 3
  } yield b * c

  f foreach println
  
  val future1 = Future.successful(4)
  val future2 = future1.filter(_ % 2 == 0)
  future2 foreach println
  
  val futureList = Future.traverse((1 to 100).toList)(x => Future(x * 2 - 1))
  val oddSum = futureList.map(_.sum)
  oddSum foreach println
  
}