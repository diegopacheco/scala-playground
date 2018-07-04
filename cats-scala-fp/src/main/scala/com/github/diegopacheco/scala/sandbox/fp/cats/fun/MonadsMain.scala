package com.github.diegopacheco.scala.sandbox.fp.cats.fun

object MonadsMain extends App {

  import cats.syntax.either._ // for asRight
  val a = 3.asRight[String]
  val b = 4.asRight[String]

  val result = for {
    x <- a
    y <- b
  } yield x * x + y * y
  println(result)
  
}