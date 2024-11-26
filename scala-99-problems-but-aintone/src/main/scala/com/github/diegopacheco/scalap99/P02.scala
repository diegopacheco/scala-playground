package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P02 (*) Find the last but one element of a list.
//     Example:
//     scala> penultimate(List(1, 1, 2, 3, 5, 8))
//     res0: Int = 5
object P02:
  def penultimateScala[A](ls: List[A]): A = if ls.isEmpty then throw new NoSuchElementException else ls.init.last

  @tailrec
  def penultimateRecursive[A](ls: List[A]): A = ls match {
    case h :: _ :: Nil => h
    case _ :: tail     => penultimateRecursive(tail)
    case _             => throw new NoSuchElementException
  }

object P02Main extends App:
  println(P02.penultimateScala(List(1, 1, 2, 3, 5, 8)))
  println(P02.penultimateRecursive(List(1, 1, 2, 3, 5, 8)))
