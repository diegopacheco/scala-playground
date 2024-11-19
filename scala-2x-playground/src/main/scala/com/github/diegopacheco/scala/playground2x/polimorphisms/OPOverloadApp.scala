package com.github.diegopacheco.scala.playground2x.polimorphisms

object imaginary {
  def apply(im: Double): Complex = Complex(0, im)
  def - (op: Complex): Complex = Complex(-op.re, -op.im)
}

case class Complex(re: Double, im: Double) {
  def + (op: Complex): Complex = Complex(re + op.re, im + op.im)
  def - (op: Complex): Complex = Complex(re - op.re, im - op.im)
  override def toString: String = s"$re + ${im}i"
}

object OPOverloadApp extends App {
  val c1 = Complex(1, 2)
  val c2 = Complex(3, 4)

  println(c1 + c2)
  println(c1 - c2)
  println(imaginary(1) + imaginary(2))
  println(imaginary(1) - imaginary(2))
}

