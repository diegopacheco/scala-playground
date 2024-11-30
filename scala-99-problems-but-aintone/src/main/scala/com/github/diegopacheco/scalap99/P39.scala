package com.github.diegopacheco.scalap99

// P39 (*) A list of prime numbers.
//     Given a range of integers by its lower and upper limit, construct a list
//     of all prime numbers in that range.
//
//     scala> listPrimesInRange(7 to 31)
//     res0: List[Int] = List(7, 11, 13, 17, 19, 23, 29, 31)
object P39:
  import com.github.diegopacheco.scalap99.P38.primes
  def listPrimesInRange(r: Range): List[Int] =
    primes.dropWhile { _ < r.start }.takeWhile { _ <= r.end }.toList

object P39Main extends App:
  import P39._
  println(listPrimesInRange(7 to 31))