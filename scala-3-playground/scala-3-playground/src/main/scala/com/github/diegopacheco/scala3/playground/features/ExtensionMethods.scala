package com.github.diegopacheco.scala3.playground.features

object ExtensionMethods extends App {
  
  case class Circle(x:Double,y:Double,radius:Double)
  
  // without extension methods
  object CircleHelpers:
    def circunference2(c:Circle):Double = c.radius - math.Pi *2 
  
  CircleHelpers.circunference2(Circle(2,2,2))

  // With extension methods
  extension (c:Circle)
    def circumference:Double = c.radius * math.Pi * 2
  
  val myCircle = Circle(2,2,2)
  val radius = myCircle.circumference 
  println(s"Circunference with extension methods == " + radius)
  
}
