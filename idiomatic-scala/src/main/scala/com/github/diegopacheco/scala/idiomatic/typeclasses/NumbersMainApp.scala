package com.github.diegopacheco.scala.idiomatic.typeclasses

object NumbersMainApp extends App {

  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def divide(x: T, y: T): T
    def minus(x: T, y: T): T
    def multiply(x: T, y: T): T
  }

  object NumberLike {
    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def divide(x: Double, y: Double): Double = x / y
      def minus(x: Double, y: Double): Double = x - y
      def multiply(x: Double, y: Double): Double = x * y
    }
    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def divide(x: Int, y: Int): Int = x / y
      def minus(x: Int, y: Int): Int = x - y
      def multiply(x: Int, y: Int): Int = x * y
    }

    implicit def int2NumberLikeInt(x: Int) = NumberLikeInt
    implicit def double2NumberLikeDouble(y: Double) = NumberLikeDouble

  }

  import NumberLike._

  val x = 10
  println(s"10 + 10 = ${x.plus(10, 10)}")
  println(s"10 - 10 = ${x.minus(10, 10)}")
  println(s"10 * 10 = ${x.multiply(10, 10)}")
  println(s"10 / 10 = ${x.divide(10, 10)}")

  val y: Double = 20.5
  println(s"20.5 + 20.5 = ${y.plus(20.5, 20.5)}")
  println(s"20.5 - 20.5 = ${y.minus(20.5, 20.5)}")
  println(s"20.5 * 20.5 = ${y.multiply(20.5, 20.5)}")
  println(s"20.5 / 20.5 = ${y.divide(20.5, 20.5)}")

}