package com.github.diegopacheco.scala.playground2x.stringss

object StringsApp extends App {

  val doc =
    """This is a
      |multiline
      |text 123
      |string""".stripMargin
  println(doc)

  val name = "John Doe"
  val age = 50
  val weight = 200.00
  println(s"Hello, $name - you are ${age + 1} years old and you weight $weight pounds")

}
