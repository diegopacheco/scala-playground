package com.github.diegopacheco.scalaplayground

//
// https://docs.scala-lang.org/scala3/book/fun-eta-expansion.html
//
object AutomaticETAExpansion extends App:

  def isLessThan(x: Int, y: Int): Boolean = x < y
  val methods = List(isLessThan)

  val result = methods.map(_(10, 20))
  println(result)
