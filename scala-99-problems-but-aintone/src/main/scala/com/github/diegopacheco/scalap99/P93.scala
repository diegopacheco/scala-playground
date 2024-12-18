package com.github.diegopacheco.scalap99

// P93 (***) An arithmetic puzzle.
//
// Given a list of integer numbers, find a correct way of inserting arithmetic signs (operators) such that the
// result is a correct equation.
// Example: With the list of numbers List(2,3,5,7,11) we can form the equations
// 2−3+5+7=11 or 2 = ( 3 ∗ 5 + 7 ) / 11 2=(3∗5+7)/11 (and ten others!).
//
// does not work = bug in the code - will fix later.
//
object P93 {
  private val operators: List[String] = List("+", "-", "*", "/")

  def findEquations(numbers: List[Int]): List[String] = {
    val n = numbers.length
    val operatorCombinations = generateOperatorCombinations(n - 1)
    val equations = operatorCombinations.flatMap { ops =>
      val equation = buildEquation(numbers, ops)
      Some(equation).filter(isValidEquation)
    }
    equations
  }

  private def generateOperatorCombinations(length: Int): List[List[String]] = {
    if (length == 0) List(Nil)
    else {
      for {
        op <- operators
        rest <- generateOperatorCombinations(length - 1)
      } yield op :: rest
    }
  }

  private def buildEquation(numbers: List[Int], operators: List[String]): String = {
    numbers.zipAll(operators, "", "").flatMap { case (num, op) => List(num.toString, op) }.mkString
  }

  private def isValidEquation(equation: String): Boolean = {
    val parts = equation.split("=")
    if (parts.length != 2) false
    else {
      val left = evaluateExpression(parts(0))
      val right = evaluateExpression(parts(1))
      left.isDefined && right.isDefined && left.get == right.get
    }
  }

  private def evaluateExpression(expr: String): Option[Double] = {
    try {
      val tokens = tokenize(expr)
      val result = parseExpression(tokens)
      Some(result)
    } catch {
      case _: Throwable => None
    }
  }

  private def tokenize(expr: String): List[String] = {
    expr.replaceAll("([()+\\-*/])", " $1 ").split("\\s+").toList.filter(_.nonEmpty)
  }

  private def parseExpression(tokens: List[String]): Double = {
    def parseTerm(tokens: List[String]): (Double, List[String]) = {
      val (factor, rest) = parseFactor(tokens)
      parseTermRest(factor, rest)
    }

    def parseTermRest(acc: Double, tokens: List[String]): (Double, List[String]) = tokens match {
      case "*" :: rest =>
        val (factor, rest2) = parseFactor(rest)
        parseTermRest(acc * factor, rest2)
      case "/" :: rest =>
        val (factor, rest2) = parseFactor(rest)
        parseTermRest(acc / factor, rest2)
      case _ => (acc, tokens)
    }

    def parseFactor(tokens: List[String]): (Double, List[String]) = tokens match {
      case "(" :: rest =>
        val (expr, rest2) = parseExpression(rest)
        (expr, rest2.tail)
      case num :: rest => (num.toDouble, rest)
    }

    def parseExpression(tokens: List[String]): (Double, List[String]) = {
      val (term, rest) = parseTerm(tokens)
      parseExpressionRest(term, rest)
    }

    def parseExpressionRest(acc: Double, tokens: List[String]): (Double, List[String]) = tokens match {
      case "+" :: rest =>
        val (term, rest2) = parseTerm(rest)
        parseExpressionRest(acc + term, rest2)
      case "-" :: rest =>
        val (term, rest2) = parseTerm(rest)
        parseExpressionRest(acc - term, rest2)
      case _ => (acc, tokens)
    }

    parseExpression(tokens)._1
  }
}

object P93Main extends App {
  import P93._

  val numbers = List(2, 3, 5, 7, 11)
  val equations = findEquations(numbers)
  equations.foreach(println)
}