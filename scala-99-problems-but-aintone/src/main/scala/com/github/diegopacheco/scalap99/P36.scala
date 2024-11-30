package com.github.diegopacheco.scalap99

// P36 (**) Determine the prime factors of a given positive integer (2).
//     Construct a list containing the prime factors and their multiplicity.
//
//     scala> 315.primeFactorMultiplicity
//     res0: List[(Int, Int)] = List((3,2), (5,1), (7,1))
//
//     Alternately, use a Map for the result.
//     scala> 315.primeFactorMultiplicity
//     res0: Map[Int,Int] = Map(3 -> 2, 5 -> 1, 7 -> 1)
object P36:
  private def solution(n: Int): List[(Int, Int)] =
    P35.primeFactors(n).groupBy(identity).
      view.mapValues(_.size).toList

  extension (n: Int)
    def primeFactorMultiplicity: List[(Int, Int)] = solution(n)

object P36Main extends App:
  import P36._
  println(315.primeFactorMultiplicity)