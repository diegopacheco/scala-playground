package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/id.html
 */
object IdMain extends App {
    
  type Id[A] = A
  
  import cats._
  
  val x: Id[Int] = 1
  val y: Int = x
  
  println( x )
  println( y )
  
  import cats.Functor
  val one: Int = 1
  println( Functor[Id].map(one)(_ + 1)) 
  
  import cats.Monad
  val two: Int = 2
  println(  Monad[Id].map(two)(_ + 2) )
  println(  Monad[Id].flatMap(two)(_ + 2) )
  
  import cats.Comonad
  println( Comonad[Id].coflatMap(two)(_ + 2) )
}