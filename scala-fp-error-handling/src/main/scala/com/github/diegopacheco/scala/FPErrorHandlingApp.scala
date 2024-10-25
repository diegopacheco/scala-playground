package com.github.diegopacheco.scala

object FPErrorHandlingApp extends App{

  //
  // Try + Pattern Matcher
  //
  import scala.util.{Try, Success, Failure}

  val result = Try("123".toInt)
  result match {
    case Success(value) => println(s"Converted to integer: $value")
    case Failure(exception) => println(s"Failed to convert: ${exception.getMessage}")
  }

  //
  // Monad Either + Pattern Matcher
  //
  def divide(a: Int, b: Int): Either[String, Int] = {
    if (b == 0) Left("Division by zero")
    else Right(a / b)
  }

  val resultDivide = divide(4, 2)
  resultDivide match {
    case Right(value) => println(s"Result: $value")
    case Left(error) => println(s"Error: $error")
  }

}
