package com.github.diegopacheco.scala.playground2x.arguments

object Repeat {
  def it(times: Int, strings: String*): String = {
      (strings.mkString(" ") + "\n") * times
  }
}

object RepeatArgsApp extends App {
  println(Repeat.it(3, "Hello", "World"))
  println(Repeat.it(3, "Scala", "is", "Awesome", "and", "Functional"))

  val lst = List("Scala", "language", "2x")
  println(Repeat.it(3, lst: _*))
}
