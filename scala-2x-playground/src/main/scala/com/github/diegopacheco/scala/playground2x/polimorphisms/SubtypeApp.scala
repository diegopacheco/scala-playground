package com.github.diegopacheco.scala.playground2x.polimorphisms

trait Shape {
  def getArea: Double
}
case class Square(side: Double) extends Shape {
  override def getArea: Double = side * side
}
case class Circle(radius: Double) extends Shape {
  override def getArea: Double = Math.PI * radius * radius
}

object Shape {
  def printArea[T <: Shape](shape: T): Double = (math.floor(shape.getArea) * 100)/100
}

object SubtypeApp extends App{
  val square = Square(10.0)
  val circle = Circle(12.0)
  println(Shape.printArea(square))
  println(Shape.printArea(circle))
}
