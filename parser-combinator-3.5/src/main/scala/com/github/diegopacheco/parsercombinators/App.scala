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

  def string(s: String): Parser[String] = input =>
    if (input.startsWith(s)) Some((s, input.drop(s.length)))
    else None

  def regex(r: String): Parser[String] = input => {
    val pattern = r.r
    pattern.findPrefixOf(input) match {
      case Some(matched) => Some((matched, input.drop(matched.length)))
      case None => None
    }
  }

  def number: Parser[Int] = regex("\\d+").map(_.toInt)

  def whitespace: Parser[String] = regex("\\s*")
}

object ArithmeticParsers {
  import Parsers._

  def term: Parser[Int] = factor ~ rep1((string("*") ~ factor) | (string("/") ~ factor)) map {
    case (f, list) => list.foldLeft(f) {
      case (acc, ("*", v)) => acc * v
      case (acc, ("/", v)) => acc / v
    }
  }

  def expr: Parser[Int] = term ~ rep1((string("+") ~ term) | (string("-") ~ term)) map {
    case (t, list) => list.foldLeft(t) {
      case (acc, ("+", v)) => acc + v
      case (acc, ("-", v)) => acc - v
    }
  }

  def factor: Parser[Int] = number | (string("(") ~> expr <~ string(")"))

  def rep1[T](p: Parser[T]): Parser[List[T]] = input => {
    p.parse(input) match {
      case Some((value, rest)) =>
        rep(p).parse(rest) match {
          case Some((values, rest2)) => Some((value :: values, rest2))
          case None => Some((List(value), rest))
        }
      case None => None
    }
  }

  def rep[T](p: Parser[T]): Parser[List[T]] = input => {
    def loop(in: String, acc: List[T]): Option[(List[T], String)] = {
      p.parse(in) match {
        case Some((value, rest)) => loop(rest, acc :+ value)
        case None => Some((acc, in))
      }
    }
    loop(input, List())
  }

  implicit class ParserOps[T](p: Parser[T]) {
    def |[U >: T](other: Parser[U]): Parser[U] = p | other
    def ~[U](other: Parser[U]): Parser[(T, U)] = p ~ other
    def map[U](f: T => U): Parser[U] = p.map(f)
    def ~>[U](other: Parser[U]): Parser[U] = (p ~ other).map(_._2)
    def <~[U](other: Parser[U]): Parser[T] = (p ~ other).map(_._1)
  }
}

object ParserCombinatorApp extends App {
  import ArithmeticParsers._

  val input = "3 + 5 * (10 - 4)"
  val result = expr.parse(input)

  result match {
    case Some((value, rest)) => println(s"Parsed value: $value, Remaining input: '$rest'")
    case None => println("Failed to parse input")
  }
}