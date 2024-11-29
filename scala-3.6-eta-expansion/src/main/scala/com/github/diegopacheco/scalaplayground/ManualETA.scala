package com.github.diegopacheco.scalaplayground

object ManualETA extends App:
  def isLessThan(x: Int, y: Int): Boolean = x < y

  val methodsA = List(isLessThan(_, _)) // way 1: wildcard application
  val methodsB = List((x, y) => isLessThan(x, y)) // way 2: anonymous function

  val res1 = methodsA.head(1, 2)
  val res2 = methodsB.head(1, 2)
  println(s"res1: ${res1} - res2: ${res2}")



