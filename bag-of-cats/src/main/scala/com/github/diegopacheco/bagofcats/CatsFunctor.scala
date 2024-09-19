package com.github.diegopacheco.bagofcats

import cats.instances.function._ // for Functor
import cats.syntax.functor._     // for map

object CatsFunctor extends App{

  val func1: Int => Double = (x: Int) => x.toDouble
  val func2: Double => Double = (y: Double) => y * 2

  // composition using map
  val res1 = (func1 map func2)(1)
  println(res1)

  // composition using andThen
  val res2 = (func1 andThen func2)(1)
  println(res2)

  // composition written out by hand
  val res3 = func2(func1(1))
  println(res3)

  val func =
    ((x: Int) => x.toDouble).
      map(x => x + 1).
      map(x => x * 2).
      map(x => s"${x}!")
  val resFunc = func(123)
  println(resFunc)
  
}
