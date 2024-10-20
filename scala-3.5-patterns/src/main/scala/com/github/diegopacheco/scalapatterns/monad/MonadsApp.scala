package com.github.diegopacheco.scalapatterns.monad

trait Monad[M[_]] {
  def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
  def unit[A](a: A): M[A]
}

object OptionMonad extends Monad[Option] {
  def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma match {
    case Some(a) => f(a)
    case None => None
  }
  def unit[A](a: A): Option[A] = Some(a)
}

object MonadsApp extends App{

  def addOne(x: Int): Option[Int] = Some(x + 1)

  def multiplyByTwo(x: Int): Option[Int] = Some(x * 2)

  val result = OptionMonad.flatMap(OptionMonad.unit(3)) { x =>
    OptionMonad.flatMap(addOne(x)) { y =>
      multiplyByTwo(y)
    }
  }
  println(result) 

}
