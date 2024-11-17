package com.github.diegopacheco.scala.playground2x.common_functions

object CommonApp extends App{

  var names = List("joel", "ed", "chris", "maurice")
  val upper = names.map(_.toUpperCase())
  println(upper)

  val capNames = names.map(_.capitalize)
  println(capNames)

  val shortNames = names.filter(_.length <= 2)
  println(shortNames)

  val word = "Planetarium"
  println(s"Word: ${word} - head: ${word.head} - tail: ${word.tail}")

  val firstTen = (1 to 100).take(10)
  println(firstTen)

  val firstFive = firstTen.takeWhile(_ <= 5)
  println(firstFive)

  names = names.drop(1)
  println(names)

  val total = Seq(1, 2, 3, 4, 5).sum
  println(total)
  println(Seq(1, 2, 3, 4, 5).reduce(_+_))
  println(Seq(1, 2, 3, 4, 5).fold(0)(_+_))

}
