package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P32 (**) Determine the greatest common divisor of two positive integer
//          numbers.
//     Use Euclid's algorithm.
//
//     scala> gcd(36, 63)
//     res0: Int = 9
object P32:
  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

object P32Main extends App:
  println(P32.gcd(36, 63))
