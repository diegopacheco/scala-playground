package com.github.diegopacheco.scalapatterns.writtermonad

case class Writer[W, A](value: A, log: W) {
  def map[B](f: A => B)(implicit monoid: Monoid[W]): Writer[W, B] =
    Writer(f(value), log)

  def flatMap[B](f: A => Writer[W, B])(implicit monoid: Monoid[W]): Writer[W, B] = {
    val next = f(value)
    Writer(next.value, monoid.combine(log, next.log))
  }
}

trait Monoid[W] {
  def empty: W
  def combine(x: W, y: W): W
}

object Monoid {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def empty: String = ""
    def combine(x: String, y: String): String = x + y
  }
}

object WritterMonadApp extends App{
  def logNumber(x: Int): Writer[String, Int] =
    Writer(x, s"Got number: $x\n")

  def multiplyByTwo(x: Int): Writer[String, Int] =
    Writer(x * 2, s"Multiplied $x by 2\n")

  def program: Writer[String, Int] = for {
    a <- logNumber(3)
    b <- multiplyByTwo(a)
  } yield b

  val result = program

  println(s"Result: ${result.value}") // Output: Result: 6
  println(s"Log: ${result.log}") // Output: Log: Got number: 3\nMultiplied 3 by 2\n

}
