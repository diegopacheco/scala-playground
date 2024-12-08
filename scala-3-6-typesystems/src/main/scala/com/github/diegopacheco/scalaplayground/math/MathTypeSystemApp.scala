package com.github.diegopacheco.scalaplayground.math

sealed trait Expr[T]
case class Const(value: Int) extends Expr[Int]
case class Add(left: Expr[Int], right: Expr[Int]) extends Expr[Int]
case class Mul(left: Expr[Int], right: Expr[Int]) extends Expr[Int]
case class Div(left: Expr[Int], right: Expr[Int]) extends Expr[Int]
case class Sub(left: Expr[Int], right: Expr[Int]) extends Expr[Int]

object ExprEvaluator {
  def eval[T](expr: Expr[T]): T = expr match {
    case Const(value) => value
    case Add(left, right) => eval(left) + eval(right)
    case Mul(left, right) => eval(left) * eval(right)
    case Div(left, right) => eval(left) / eval(right)
    case Sub(left, right) => eval(left) - eval(right)
  }
}

object MathTypeSystemApp extends App{
  import ExprEvaluator._

  private val expr: Expr[Int] = Add(Const(1), Mul(Const(2), Const(3)))
  println(s"Result: ${eval(expr)}") // Result: 7
}

