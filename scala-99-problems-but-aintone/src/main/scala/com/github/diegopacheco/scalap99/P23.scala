package com.github.diegopacheco.scalap99

// P23 (**) Extract a given number of randomly selected elements from a list.
//     Example:
//     scala> randomSelect(3, List('a', 'b', 'c', 'd', 'f', 'g', 'h'))
//     res0: List[Symbol] = List('e', 'd', 'a')
//
//     Hint: Use the answer to P20.
object P23:
  def randomSelect[T](n: Int, ls: List[T]): List[T] =
    if (n <= 0) return Nil
    val (rest, e) = P20.removeAt((new util.Random).nextInt(ls.length), ls)
    e :: randomSelect(n - 1, rest)

object P23Main extends App:
  println(P23.randomSelect(3, List('a', 'b', 'c', 'd', 'f', 'g', 'h')))