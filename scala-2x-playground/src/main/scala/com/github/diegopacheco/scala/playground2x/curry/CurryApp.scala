package com.github.diegopacheco.scala.playground2x.curry

object MathOps {
  def add(x: Int)(y: Int): Int = x + y
}

object AreaCalculator {
  def rectangleArea(length: Double)(width: Double): Double = length * width
  def circleArea(radius: Double)(pi: Double = Math.PI): Double = pi * radius * radius
}

object CurryApp extends App{
  val add2 = MathOps.add(2)_
  val add5 = MathOps.add(5)_

  println(add2(3))
  println(add5(3))

  val rectangleArea5 = AreaCalculator.rectangleArea(5) _
  val circleArea3 = AreaCalculator.circleArea(3) _
  println(rectangleArea5(10))    // Output: 50.0
  println(circleArea3(Math.PI))  // Output: 28.274333882308138
}
