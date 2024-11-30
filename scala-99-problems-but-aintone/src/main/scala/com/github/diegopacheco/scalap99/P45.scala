package com.github.diegopacheco.scalap99

// P45 - A list of Goldbach compositions (4)
//
// Given a range of integers by its lower and upper limit, print a list
// of all even numbers and their Goldbach composition.
// > printGoldbachList(9..20)
// 10 = 3 + 7
// 12 = 5 + 7
// 14 = 3 + 11
// 16 = 3 + 13
// 18 = 5 + 13
// 20 = 3 + 17
//
// In most cases, if an even number is written as the sum of two prime
// numbers, one of them is very small. Very rarely, the primes are both
// bigger than, say, 50. Example (minimum value of 50 for the primes):
// > printGoldbachListLimited(2..3000, 50)
// 992 = 73 + 919
// 1382 = 61 + 1321
// 1856 = 67 + 1789
// ...
object P45:
    def isPrime(n: Int): Boolean =
      if (n <= 1) false
      else if (n == 2) true
      else !(2 until n).exists(x => n % x == 0)

    def primes(r: Range): List[Int] =
      r.filter(isPrime).toList

    def goldbach(n: Int): List[(Int,Int)] =
      val p = primes(2 to n)
      p.flatMap(x => p.filter(_ + x == n).map((x,_)))

    def printGoldbachList(r: Range): Unit =
      r.filter(_ % 2 == 0).
        foreach(n => goldbach(n).
          foreach{ case (a,b) => println(s"$n = $a + $b") })

    def printGoldbachListLimited(r: Range, limit: Int): Unit = {
      r.filter(_ % 2 == 0).
        foreach(n => goldbach(n).
          foreach{
            case (a,b) =>
              if (a > limit && b > limit) println(s"$n = $a + $b") })
    }

object P45Main extends App:
  P45.printGoldbachList(9 to 20)
  P45.printGoldbachListLimited(2 to 3000, 50)

