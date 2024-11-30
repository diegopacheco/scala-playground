package com.github.diegopacheco.scalap99

// P33 (*) Determine whether two positive integer numbers are coprime.
//     Two numbers are coprime if their greatest common divisor equals 1.
//
//     scala> 35.isCoprimeTo(64)
//     res0: Boolean = true
object P33:
  extension (n: Int)
    def isCoprimeTo(m: Int): Boolean =
      P32.gcd(n, m) == 1

object P33Main extends App:
  import P33._
  println(35.isCoprimeTo(64))
