package com.github.diegopacheco.scalapatterns.freemonad

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

sealed trait Free[F[_], A] {
  def map[B](f: A => B)(implicit F: Functor[F]): Free[F, B] = this match {
    case Pure(a) => Pure(f(a))
    case Suspend(fa) => Suspend(F.map(fa)(_.map(f)))
    case FlatMap(sub, k) => FlatMap(sub, k andThen (_.map(f)))
  }

  def flatMap[B](f: A => Free[F, B]): Free[F, B] = this match {
    case Pure(a) => f(a)
    case Suspend(fa) => FlatMap(this, f)
    case FlatMap(sub, k) => FlatMap(sub, k andThen (_.flatMap(f)))
  }
}

case class Pure[F[_], A](a: A) extends Free[F, A]
case class Suspend[F[_], A](fa: F[Free[F, A]]) extends Free[F, A]
case class FlatMap[F[_], A, B](sub: Free[F, A], k: A => Free[F, B]) extends Free[F, B]

sealed trait Console[A]

case class PrintLine(line: String) extends Console[Unit]
case class ReadLine() extends Console[String]

object Console {
  implicit val consoleFunctor: Functor[Console] = new Functor[Console] {
    def map[A, B](fa: Console[A])(f: A => B): Console[B] = fa match {
      case PrintLine(line) => PrintLine(line).asInstanceOf[Console[B]]
      case ReadLine() => ReadLine().asInstanceOf[Console[B]]
    }
  }

  type ConsoleFree[A] = Free[Console, A]

  def printLine(line: String): ConsoleFree[Unit] = Suspend(PrintLine(line).asInstanceOf[Console[Free[Console, Unit]]])
  def readLine(): ConsoleFree[String] = Suspend(ReadLine().asInstanceOf[Console[Free[Console, String]]])
}

object FreeMonadApp extends App {
  import Console._

  val program: ConsoleFree[Unit] = for {
    _ <- printLine("What's your name?")
    name <- readLine()
    _ <- printLine(s"Hello, $name!")
  } yield ()

  println(program)
}