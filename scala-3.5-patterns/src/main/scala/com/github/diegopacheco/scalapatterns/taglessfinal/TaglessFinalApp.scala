package com.github.diegopacheco.scalapatterns.taglessfinal

trait Expr[F[_]] {
  def lit(value: Int): F[Int]
  def add(x: F[Int], y: F[Int]): F[Int]
}

object Eval extends Expr[Option] {
  def lit(value: Int): Option[Int] = Some(value)
  def add(x: Option[Int], y: Option[Int]): Option[Int] = for {
    a <- x
    b <- y
  } yield a + b
}

type Id[A] = A

object Print extends Expr[Id] {
  def lit(value: Int): Id[Int] = value
  def add(x: Id[Int], y: Id[Int]): Id[Int] = x + y
}

object TaglessFinalApp extends App {
  def program[F[_]](implicit E: Expr[F]): F[Int] = {
    import E._
    add(lit(1), add(lit(2), lit(3)))
  }

  println(program(implicitly[Expr[Option]](Eval))) // Output: Some(6)
  println(program(implicitly[Expr[Id]](Print)))    // Output: 6
}