package com.github.diegopacheco.parsercombinators

trait Parser[+T] {
  def parse(input: String): Option[(T, String)]

  def |[U >: T](other: Parser[U]): Parser[U] = input =>
    this.parse(input) match {
      case success @ Some(_) => success
      case None => other.parse(input)
    }

  def ~[U](other: Parser[U]): Parser[(T, U)] = input =>
    this.parse(input) match {
      case Some((value1, rest1)) =>
        other.parse(rest1) match {
          case Some((value2, rest2)) => Some(((value1, value2), rest2))
          case None => None
        }
      case None => None
    }

  def map[U](f: T => U): Parser[U] = input =>
    this.parse(input) match {
      case Some((value, rest)) => Some((f(value), rest))
      case None => None
    }
}

object Parsers {
  def char(c: Char): Parser[Char] = input =>
    if (input.nonEmpty && input.head == c) Some((c, input.tail))
    else None

  def number: Parser[Int] = input => {
    val num = input.takeWhile(_.isDigit)
    if (num.nonEmpty) Some((num.toInt, input.drop(num.length)))
    else None
  }
}

object ArithmeticParsers {
  import Parsers._

  def factor: Parser[Int] = number

  def term: Parser[Int] = factor ~ opt(char('*') ~ factor) map {
    case (f, Some((_, v))) => f * v
    case (f, None) => f
  }

  def expr: Parser[Int] = term ~ opt(char('+') ~ term) map {
    case (t, Some((_, v))) => t + v
    case (t, None) => t
  }

  def opt[T](p: Parser[T]): Parser[Option[T]] = input =>
    p.parse(input) match {
      case Some((value, rest)) => Some((Some(value), rest))
      case None => Some((None, input))
    }
}

object ParserCombinatorApp extends App {
  import ArithmeticParsers._

  val input = "3+5*2"
  val result = expr.parse(input)

  result match {
    case Some((value, rest)) => println(s"Parsed value: $value, Remaining input: '$rest'")
    case None => println("Failed to parse input")
  }
}