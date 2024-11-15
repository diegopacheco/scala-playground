package com.github.diegopacheco.scalaplayground.monad

// Option is a Monad
trait Monad[A] {
  def flatMap[B](f: A => Monad[B]): Monad[B]
  def map[B](f: A => B): Monad[B]
}

def split(str: String): Option[(String, String)] = {
  val parts = str.split("/")
  if (parts.length == 2) Some(parts(0), parts(1)) else None
}

def parse(p: (String, String)): Option[(Double, Double)] = {
  try {
    Some(p._1.toDouble, p._2.toDouble)
  } catch {
    case e: Exception => None
  }
}

def divide(n: (Double, Double)): Option[Double] = {
  if (n._2 == 0) None else Some(n._1 / n._2)
}

object GreatMonadApp extends App {

  //
  // ugly ifs but safe thanks to monad (Option)
  //
  val result1 = {
    val resSplit = split("100/10")
    if (resSplit.nonEmpty) {
      val resParse = parse(resSplit.get)
      if (resParse.nonEmpty) {
        val resDivide = divide(resParse.get)
        if (resDivide.nonEmpty) {
          resDivide.get
        } else None
      } else None
    } else None
  }
  println(result1)

  //
  // Pattern matching but still ugly
  //
  val result2 = split("100/10") match {
    case Some((a, b)) => parse((a, b)) match {
      case Some((x, y)) => divide((x, y)) match {
        case Some(result) => Some(result)
        case None => None
      }
      case None => None
    }
    case None => None
  }
  println(result2)

  //
  // just flatmap (monad)
  //
  val result3 = split("100/10")
    .flatMap(parse)
    .flatMap(divide)
  println(result3)

  //
  // monad (flatmap) composition
  // with for comprehension
  //
  val result4 = for {
    s <- split("100/10")
    p <- parse(s)
    d <- divide(p)
  } yield d
  println(result4)

}
