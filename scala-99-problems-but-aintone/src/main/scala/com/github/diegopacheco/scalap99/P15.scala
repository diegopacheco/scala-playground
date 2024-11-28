package com.github.diegopacheco.scalap99

// P15 (**) Duplicate the elements of a list a given number of times.
//     Example:
//     scala> duplicateN(3, List('a', 'b', 'c', 'c', 'd'))
//     res0: List[Symbol] = List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd')
object P15:
  def duplicateN[A](ls: List[A], n: Int): List[A] =
    ls.flatMap(e => List.fill(n)(e))

object P15Main extends App:
  println(P15.duplicateN(List('a', 'b', 'c', 'c', 'd'), 3))