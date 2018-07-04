package com.github.diegopacheco.scala.sandbox.fp.cats.fun

object MonoidFun extends App {
  
  import cats.Monoid
  import cats.instances.int._
  import cats.instances.option._ 
  
  val a = Option(22)
  val b = Option(20)
  val r = Monoid[Option[Int]].combine(a, b)
  println(r)
  
}