package com.github.diegopacheco.scalaplayground

object SimpleCalculator {
  def add(x: Int, y: Int): Int = x + y
  def sub(x: Int, y: Int): Int = x - y
  def mul(x: Int, y: Int): Int = x * y
  def div(x: Int, y: Int): Int = x / y
  def mod(x: Int, y: Int): Int = x % y
  def pow(x: Int, y: Int): Int = Math.pow(x, y).toInt
}
