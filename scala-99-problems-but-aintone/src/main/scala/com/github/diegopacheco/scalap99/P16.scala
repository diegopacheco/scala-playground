package com.github.diegopacheco.scalap99

// P16 (**) Drop every Nth element from a list.
//     Example:
//     scala> drop(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
//     res0: List[Symbol] = List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')
object P16:
  def drop3[A](n: Int, ls: List[A]): List[A] =
    ls.zipWithIndex.filterNot(_._2 % n == n - 1).map(_._1)

object P16Main extends App:
  println(P16.drop3(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))