package com.github.diegopacheco.scala.playground2x.polimorphisms

//
// With parametric polymorphism, the logic remains the same for all the different types
//
object ParametricApp extends App{

  def pairWiseReverse[A](xs:List[A]): List[A] = xs.grouped(2).flatMap(_.reverse).toList

  val xs = List(1,2,3,4,5,6,7,8,9,10)
  println(pairWiseReverse(xs))

  val ys = List("a","b","c","d","e","f","g","h","i","j")
  println(pairWiseReverse(ys))

}
