package com.github.diegopacheco.scala.sandbox.fp.cats.typeclasses

/**
 * Monoid
 * Monoid extends the power of Semigroup by providing an additional empty value.
 * 
 * trait Semigroup[A] {
 *   def combine(x: A, y: A): A
 * }
 *
 * trait Monoid[A] extends Semigroup[A] {
 *   def empty: A
 * }
 * 
 * This empty value should be an identity for the combine operation, which means the following equalities hold for any choice of x.
 * combine(x, empty) = combine(empty, x) = x
 * 
 * Many types that form a Semigroup also form a Monoid, such as Ints (with 0) and Strings (with "").
 * 
 */
object MonoidMain extends App {
  
  import cats.Monoid

  implicit val intAdditionMonoid: Monoid[Int] = new Monoid[Int] {
    def empty: Int = 0
    def combine(x: Int, y: Int): Int = x + y
  }

  val x = 1
  println( Monoid[Int].combine(x, Monoid[Int].empty) )
  println( Monoid[Int].combine(Monoid[Int].empty, x) )
  
  import cats.instances.all._
  
  def combineAll[A: Monoid](as: List[A]): A = as.foldLeft(Monoid[A].empty)(Monoid[A].combine)
  
  println( combineAll(List(1, 2, 3)) )
  println( combineAll(List("hello", " ", "world")) ) 
  println( combineAll(List(Map('a' -> 1), Map('a' -> 2, 'b' -> 3), Map('b' -> 4, 'c' -> 5))) )
  println( combineAll(List(Set(1, 2), Set(2, 3, 4, 5)))  )
  
  
}