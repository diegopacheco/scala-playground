package com.github.diegopacheco.scalap99

// P34 (**) Calculate Euler's totient function phi(m).
//     Euler's so-called totient function phi(m) is defined as the number of
//     positive integers r (1 <= r < m) that are coprime to m.  As a special
//     case, phi(1) is defined to be 1.
//
//     scala> 10.totient
//     res0: Int = 4
object P34:
  extension (n: Int)
    def totient: Int =
      (1 to n).count((i: Int) => P32.gcd(n, i) == 1)

object P34Main extends App:
  import P34._
  println(10.totient)