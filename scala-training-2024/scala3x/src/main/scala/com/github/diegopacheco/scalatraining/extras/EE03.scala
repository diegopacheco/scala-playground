package com.github.diegopacheco.scalatraining.extras

import scala.annotation.tailrec

/*
 * Implement a quick sort in Scala.
 */
object EE03:
  def quicksort(xs: List[Int]): List[Int] = xs match
    case Nil => Nil
    case pivot :: tail =>
      val (less, greater) = tail.partition(_ < pivot)
      quicksort(less) ::: pivot :: quicksort(greater)

object EE03Main extends App:
  val sorted = EE03.quicksort(List(5, 3, 1, 2, 4))
  println(sorted)
