package com.github.diegopacheco.scalapatterns.foldable

trait Foldable[F[_]] {
  def foldLeft[A, B](fa: F[A], z: B)(f: (B, A) => B): B
  def foldRight[A, B](fa: F[A], z: B)(f: (A, B) => B): B
}

object ListFoldable extends Foldable[List] {
  def foldLeft[A, B](fa: List[A], z: B)(f: (B, A) => B): B = fa.foldLeft(z)(f)
  def foldRight[A, B](fa: List[A], z: B)(f: (A, B) => B): B = fa.foldRight(z)(f)
}

object FoldableApp extends App{
  val list = List(1, 2, 3, 4)
  val sum = ListFoldable.foldLeft(list, 0)(_ + _)
  val product = ListFoldable.foldRight(list, 1)(_ * _)

  println(sum) // Output: 10
  println(product) // Output: 24
}
