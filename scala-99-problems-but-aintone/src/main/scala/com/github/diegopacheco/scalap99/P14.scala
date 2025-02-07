package com.github.diegopacheco.scalap99

// P14 (*) Duplicate the elements of a list.
//     Example:
//     scala> duplicate(List('a', 'b', 'c', 'c', 'd'))
//     res0: List[Symbol] = List('a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'd', 'd')
object P14:
  def duplicate[A](ls: List[A]): List[A] =
    ls.flatMap(e => List(e, e))

object P14Main extends App:
  println(P14.duplicate(List('a', 'b', 'c', 'c', 'd')))