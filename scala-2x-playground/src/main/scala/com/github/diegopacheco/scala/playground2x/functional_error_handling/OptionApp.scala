package com.github.diegopacheco.scala.playground2x.functional_error_handling


object OptionApp extends App {

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  println(toInt("1"))
  println(toInt("x"))

  // pattern matcher
  toInt("23") match {
    case Some(i) => println(i)
    case None => println("That didn't work.")
  }

  val y = for {
    a <- toInt("41")
    b <- toInt("1")
    c <- toInt("0")
  } yield a + b + c
  println(y)

}
