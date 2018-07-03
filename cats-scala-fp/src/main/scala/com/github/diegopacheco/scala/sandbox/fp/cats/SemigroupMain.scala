package com.github.diegopacheco.scala.sandbox.fp.cats

/**
 * If a type A can form a Semigroup it has an associative binary operation.
 * Associativity means the following equality must hold for any choice of x, y, and z.
 * combine(x, combine(y, z)) = combine(combine(x, y), z)
 * 
 * trait Semigroup[A] {
 *   def combine(x: A, y: A): A
 * }
 * 
 */
object SemigroupMain extends App {

  import cats.Semigroup

  implicit val intAdditionSemigroup: Semigroup[Int] = new Semigroup[Int] {
    def combine(x: Int, y: Int): Int = x + y
  }

  val x = 1
  val y = 2
  val z = 3

  println(Semigroup[Int].combine(x, y))
  println(Semigroup[Int].combine(x, Semigroup[Int].combine(y, z)))
  println(Semigroup[Int].combine(Semigroup[Int].combine(x, y), z))
  
  import cats.syntax.semigroup._
  println( 1 |+| 2 )
  
  import cats.instances.map._
  val map1 = Map("hello" -> 0, "world" -> 1)
  val map2 = Map("hello" -> 2, "cats"  -> 3)
  
  Semigroup[Map[String, Int]].combine(map1, map2)
  println( map1 |+| map2 )
  
}