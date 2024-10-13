package com.github.diegopacheco.bagofcats.exercises

trait PrintableV3[A] {
  def format(value: A): String
}

object PrintableV3 {
  def format[A](input: A)(implicit p: PrintableV3[A]): String =
    p.format(input)

  def print[A](input: A)(implicit p: PrintableV3[A]): Unit =
    println(format(input))
}

object PrintableInstancesV3 {
  implicit val stringPrintable: PrintableV3[String] = new PrintableV3[String] {
    def format(input: String): String = input
  }
  implicit val intPrintable: PrintableV3[Int] = new PrintableV3[Int] {
    def format(input: Int): String = input.toString
  }
  implicit val catPrintable: PrintableV3[JustACat] = new PrintableV3[JustACat] {
    def format(cat: JustACat): String = {
      val name = PrintableV3.format(cat.name)
      val age = PrintableV3.format(cat.age)
      val color = PrintableV3.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}

final case class JustACat(name: String, age: Int, color: String)

object PrintableSyntaxV3 {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: PrintableV3[A]): String =
      p.format(value)

    def print(implicit p: PrintableV3[A]): Unit =
      println(format(p))
  }
}

object PrintableLibAppSyntaxV3 extends App {
  import PrintableInstancesV3._
  import PrintableSyntaxV3._

  JustACat("Garfield", 41, "ginger and black").print
}