package com.github.diegopacheco.scalap99

// P24 (*) Lotto: Draw N different random numbers from the set 1..M.
//     Example:
//     scala> lotto(6, 49)
//     res0: List[Int] = List(23, 1, 17, 33, 21, 37)
object P24:
  def lotto(n: Int, m: Int): List[Int] =
    P23.randomSelect(n, List.range(1, m+1))

object P24Main extends App:
  println(P24.lotto(6, 49))

