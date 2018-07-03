package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/nel.html
 */
object NonEmptyListMain extends App {
  
  import cats.data.NonEmptyList
  
  val l = NonEmptyList.one(42)
  println(l)
  
  println( NonEmptyList.of(1, 2, 3, 4) )
  
  import cats.syntax.list
  import cats.syntax.list._
  
  println( NonEmptyList.fromList(List(1,2,3)) )
  
}