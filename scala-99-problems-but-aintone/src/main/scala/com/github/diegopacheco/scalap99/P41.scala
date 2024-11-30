package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P41.printGoldbachList

// P41 (**) A list of Goldbach compositions.
//     Given a range of integers by its lower and upper limit, print a list of
//     all even numbers and their Goldbach composition.
//
//     scala> printGoldbachList(9 to 20)
//     10 = 3 + 7
//     12 = 5 + 7
//     14 = 3 + 11
//     16 = 3 + 13
//     18 = 5 + 13
//     20 = 3 + 17
//
//     In most cases, if an even number is written as the sum of two prime
//     numbers, one of them is very small.  Very rarely, the primes are both
//     bigger than, say, 50.  Try to find out how many such cases there are in
//     the range 2..3000.
//
//     Example (minimum value of 50 for the primes):
//     scala> printGoldbachListLimited(1 to 2000, 50)
//     992 = 73 + 919
//     1382 = 61 + 1321
//     1856 = 67 + 1789
//     1928 = 61 + 1867
object P41:
  def isPrime(n: Int): Boolean =
    if (n <= 1) false
    else if (n == 2) true
    else !(2 until n).exists(x => n % x == 0)

  private def primesInRange(r: Range): List[Int] =
    r.filter(isPrime).toList

  def goldbach(n: Int): (Int, Int) =
    val primes = primesInRange(2 to n)
    primes.map(p => (p, n - p)).find {
      case (p1, p2) => primes.contains(p2)
    }.getOrElse(throw new IllegalArgumentException(s"No Goldbach composition found for $n"))

  def printGoldbachList(r: Range): Unit =
    r.filter(n => n % 2 == 0 && n > 2).foreach(n => {
      val (p1, p2) = goldbach(n)
      println(s"$n = $p1 + $p2")
    })

  def printGoldbachListLimited(r: Range, limit: Int): Unit =
    r.filter(n => n % 2 == 0 && n > 2).foreach(n => {
      val (p1, p2) = goldbach(n)
      if (p1 > limit && p2 > limit) println(s"$n = $p1 + $p2")
    })

object P41Main extends App:
  import P41.*
  printGoldbachList(9 to 20)
  printGoldbachListLimited(1 to 2000, 50)