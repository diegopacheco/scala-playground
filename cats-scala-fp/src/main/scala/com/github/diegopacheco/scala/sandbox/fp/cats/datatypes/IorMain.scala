package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/ior.html
 */
object IorMain extends App {
  
  import cats.data._
  
  val right = Ior.right[String, Int](3)
  println(right)
  
  val left = Ior.left[String, Int]("Error")
  println(left)
  
  val both = Ior.both("Warning", 3)
  println(both)
  
  import cats.syntax.ior._
  
  val right2 = 3.rightIor
  println(right2)
  
  val left2 = "Error".leftIor
  println(left2)
  
  println( Ior.both("Warning", 42).toEither )
  
}
