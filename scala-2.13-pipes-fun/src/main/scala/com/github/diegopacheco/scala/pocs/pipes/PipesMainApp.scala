package com.github.diegopacheco.scala.pocs.pipes

import scala.util.chaining._
import scala.language.implicitConversions

object PipesMainApp extends App {

  def plus1(i: Int) = i + 1
  def double(i: Int) = i * 2
  def square(i: Int) = i * i

  val x = 1.pipe(plus1).pipe(double).pipe(square)
  println(s"x is == ${x}")

  val x2 = 1.pipe(plus1)
            .pipe(double)
            .tap(res => println(s"DEBUG: x = $res"))
  println(s"x2 is == ${x2}")

  implicit class Piper[A](val a: A) {
    import scala.util.chaining._
    implicit def |||[B](f: (A) => B): B = a.pipe(f)
  }
  val x3 = 1 ||| plus1 ||| double ||| square
  println(s"x3 is == ${x3}")

}
