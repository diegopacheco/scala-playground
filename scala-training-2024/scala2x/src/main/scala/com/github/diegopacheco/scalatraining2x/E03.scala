package com.github.diegopacheco.scalatraining2x

/**
 * using monads implement the parser "10*10" = 100
 */
object E03 {

  def split(s: String): Option[Array[String]] = {
    val tokens = s.split("\\*")
    if (tokens.size != 2) return None
    Some(tokens)
  }

  def parse(exp:Array[String]): Option[(Int,Int)] = {
    val a = exp(0).toInt
    val b = exp(1).toInt
    Some(a,b)
  }

  def eval(a: Int, b: Int): Int = a * b

  def run(s: String): Option[Int] = {
    val result = for {
      tokens <- split(s)
      parsed <- parse(tokens)
      result = eval(parsed._1, parsed._2)
    } yield result
    result
  }

}

object E03App extends App {
  println(s"10*10==" + E03.run("10*10").getOrElse(0))
}