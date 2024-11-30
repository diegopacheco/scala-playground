package com.github.diegopacheco.scalap99

// P25 (*) Generate a random permutation of the elements of a list.
//     Hint: Use the solution of problem P23.
//
//     Example:
//     scala> randomPermute(List('a', 'b', 'c', 'd', 'e', 'f'))
//     res0: List[Symbol] = List('b', 'a', 'd', 'c', 'e', 'f')
object P25:
    def randomPermute[A](ls: List[A]): List[A] =
      P23.randomSelect(ls.size, ls)

object P25Main extends App:
  println(P25.randomPermute(List('a', 'b', 'c', 'd', 'e', 'f')))