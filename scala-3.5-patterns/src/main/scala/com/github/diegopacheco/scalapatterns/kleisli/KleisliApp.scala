package com.github.diegopacheco.scalapatterns.kleisli

trait Monad[M[_]] {
  def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
  def unit[A](a: A): M[A]
  def map[A, B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))
}

object OptionMonad extends Monad[Option] {
  def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma match {
    case Some(a) => f(a)
    case None => None
  }
  def unit[A](a: A): Option[A] = Some(a)
}

trait Kleisli[M[_], A, B] {
  def run(a: A): M[B]

  def flatMap[C](f: B => Kleisli[M, A, C])(implicit M: Monad[M]): Kleisli[M, A, C] = Kleisli { a =>
    M.flatMap(run(a))(b => f(b).run(a))
  }

  def map[C](f: B => C)(implicit M: Monad[M]): Kleisli[M, A, C] = Kleisli { a =>
    M.map(run(a))(f)
  }
}

object Kleisli {
  def apply[M[_], A, B](f: A => M[B]): Kleisli[M, A, B] = new Kleisli[M, A, B] {
    def run(a: A): M[B] = f(a)
  }
}

object KleisliApp extends App {
  implicit val optionMonad: Monad[Option] = OptionMonad

  val addOne: Kleisli[Option, Int, Int] = Kleisli(x => Some(x + 1))
  val multiplyByTwo: Kleisli[Option, Int, Int] = Kleisli(x => Some(x * 2))

  val combined: Kleisli[Option, Int, Int] = for {
    a <- addOne
    b <- multiplyByTwo
  } yield b

  val result = combined.run(3)
  println(result) // Output: Some(8)
}