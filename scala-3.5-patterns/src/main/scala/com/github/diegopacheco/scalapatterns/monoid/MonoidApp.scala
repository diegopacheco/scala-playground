package com.github.diegopacheco.scalapatterns.monoid

trait Monoid[A] {
  def combine(x: A, y: A): A
  def empty: A
}

object IntMonoid extends Monoid[Int] {
  def combine(x: Int, y: Int): Int = x + y
  def empty: Int = 0
}

object MonoidApp extends App{
  val a = 3
  val b = 5
  val result = IntMonoid.combine(a, b)
  val identity = IntMonoid.empty

  println(result) // Output: 8
  println(identity) // Output: 0
}
