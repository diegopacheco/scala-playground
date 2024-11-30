package com.github.diegopacheco.scalap99

// P21 (*) Insert an element at a given position into a list.
//     Example:
//     scala> insertAt('new, 1, List('a', 'b', 'c', 'd'))
//     res0: List[Symbol] = List('a', 'new', 'b', 'c', 'd')
object P21:
  def insertAt[A](e: A, n: Int, ls: List[A]): List[A] = ls.splitAt(n) match
    case (pre, post) => pre ::: e :: post

  def insertAt2[A](e: A, n: Int, ls: List[A]): List[A] =
    ls.take(n) ::: e :: ls.drop(n)

object P21Main extends App:
    println(P21.insertAt("new", 1, List('a', 'b', 'c', 'd')))
    println(P21.insertAt2("new", 1, List('a', 'b', 'c', 'd')))
