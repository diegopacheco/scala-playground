package com.github.diegopacheco.scala.sandbox.fp.cats.fun

object FunctorLifiting extends App {
  
  import scala.language.higherKinds
  import cats.Functor
  import cats.instances.option._
  
  val func = (x: Int) => x + 1
  val liftedFunc = Functor[Option].lift(func)
  val result = liftedFunc(Option(1))
  println(result)
  
}