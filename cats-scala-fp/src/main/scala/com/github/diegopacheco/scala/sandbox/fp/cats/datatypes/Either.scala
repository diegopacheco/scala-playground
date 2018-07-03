package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/either.html
 */
object Either extends App {
  
  val e1: Either[String, Int] = Right(5)
  println( e1.right.map(_ + 1) )

  val e2: Either[String, Int] = Left("hello")
  println( e2.right.map(_ + 1) )
  
  
  import cats.syntax.either._
  
  val right: Either[String, Int] = Right(5)
  println( right.map(_ + 1) )
  
  val left: Either[String, Int] = Left("hello")
  println( left.map(_ + 1) )
  
}
