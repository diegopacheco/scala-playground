package com.github.diegopacheco.scala.playground.shade.memcached

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ShadeMemcachedApp extends App {

  import shade.memcached._
  import scala.concurrent.ExecutionContext.Implicits.global

  val memcached = Memcached(Configuration("127.0.0.1:11211"))

  val op:Future[Boolean] = memcached.add("username", "Diego", 1.minute)
  op.onComplete( b => println(s"Inserted in memcached? ${b}"))

  val result: Future[Option[String]] = memcached.get[String]("username")
  result.onComplete( k => println(s"Memcached result is: ${k.get} ") )

  Thread.sleep(1000L)

}

