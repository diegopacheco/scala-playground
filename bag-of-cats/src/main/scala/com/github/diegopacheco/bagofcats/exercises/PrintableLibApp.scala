package com.github.diegopacheco.bagofcats.exercises

final case class Cat(name: String, age: Int, color: String)

trait PrintableLib[A] {
  def format(value: A): String
}

object PrintableLib {
  def format[A](value: A)(implicit p: PrintableLib[A]): String = p.format(value)
}

object PrintableLibInstances {
  import PrintableLib._

  implicit val stringPrintable: PrintableLib[String] = new PrintableLib[String] {
    def format(value: String): String = value
  }

  implicit val intPrintable: PrintableLib[Int] = new PrintableLib[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val catPrintable: PrintableLib[Cat] = new PrintableLib[Cat] {
    def format(cat: Cat): String = {
      val name = PrintableLib.format(cat.name)
      val age = PrintableLib.format(cat.age)
      val color = PrintableLib.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}

object PrintableLibApp extends App {
  import PrintableLibInstances._
  import PrintableLib._

  val cat = Cat("Garfield", 38, "ginger and black")
  println(PrintableLib.format(cat))
}