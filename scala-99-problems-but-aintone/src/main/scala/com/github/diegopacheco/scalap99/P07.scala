package com.github.diegopacheco.scalap99

import scala.Console.println

// P07 (**) Flatten a nested list structure.
//     Example:
//     scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
//     res0: List[Any] = List(1, 1, 2, 3, 5, 8)
object P07:
  def flattenScala[A](list: List[List[A]]): List[A] = list.flatten

  def flatten(list: List[Any]): List[Any] = list match
    case Nil => Nil
    case (x: List[Any]) :: xs => flatten(x) ::: flatten(xs)
    case x :: xs => x :: flatten(xs)

object P07Main extends App:
  println(P07.flattenScala(List(List(1, 1), List(2), List(3, List(5, 8)))))
  println(P07.flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
