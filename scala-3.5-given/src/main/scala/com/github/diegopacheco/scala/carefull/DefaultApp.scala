package com.github.diegopacheco.scala.carefull

trait Combiner[A]{
  def combine(a:A,b:A): A
}

def combineAll[A](list:List[A])(implicit c:Combiner[A]):A =
  list.reduce(c.combine)

given Combiner[Int] with
  def combine(a:Int,b:Int):Int = a + b

given Combiner[String] with
  def combine(a:String,b:String):String = a + b

object DefaultApp extends App{
  import scala.language.implicitConversions

  val numbers = List(1,2,3,4,5)
  val result = combineAll(numbers)
  println(result)

  val strings = List("a","b","c","d","e")
  val result2 = combineAll(strings)
  println(result2)

}
