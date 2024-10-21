package com.github.diegopacheco.scalapatterns.semigroup

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

object IntSemigroup extends Semigroup[Int] {
  def combine(x: Int, y: Int): Int = x + y
}

object SemigroupApp extends App{
  val a = 3
  val b = 5
  val result = IntSemigroup.combine(a, b)
  println(result) // Output: 8
}
