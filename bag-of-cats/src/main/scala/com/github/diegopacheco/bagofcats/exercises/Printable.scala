package com.github.diegopacheco.bagofcats.exercises

object Printable extends App{

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val stringPrintable = new Printable[String] {
      def format(input: String) = input
    }
    implicit val intPrintable = new Printable[Int] {
      def format(input: Int) = input.toString
    }
  }

  object Printable {
    def format[A](input: A)(implicit p: Printable[A]): String =
      p.format(input)

    def print[A](input: A)(implicit p: Printable[A]): Unit =
      println(format(input))
  }

  val a = "Hello"
  val b = 10

  import PrintableInstances._
  Printable.print(a)
  Printable.print(b)

}
