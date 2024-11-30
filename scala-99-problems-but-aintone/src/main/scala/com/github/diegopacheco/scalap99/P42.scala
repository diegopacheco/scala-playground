package com.github.diegopacheco.scalap99


// P42 - A list of Goldbach compositions(1)
//
// Given a range of integers by its lower and upper limit, print a
// list of all even numbers and their Goldbach composition.
//
// scala> printGoldbachList(9 to 20)
// 10 = 3 + 7
// 12 = 5 + 7
// 14 = 3 + 11
// 16 = 3 + 13
// 18 = 5 + 13
// 20 = 3 + 17
//
object P42:
    def isPrime(n: Int): Boolean = {
      if (n <= 1) return false
      if (n == 2) return true
      if (n % 2 == 0) return false
      val max = Math.sqrt(n).toInt
      for (i <- 3 to max by 2)
        if (n % i == 0) return false
      true
    }

    def goldbach(n: Int): (Int, Int) = {
      if (n <= 2 || n % 2 != 0) throw new IllegalArgumentException
      for (i <- 2 to n / 2)
        if (isPrime(i) && isPrime(n - i))
          return (i, n - i)
      throw new IllegalArgumentException
    }

    def printGoldbachList(range: Range): Unit = {
      for (n <- range if n % 2 == 0) {
        val (p1, p2) = goldbach(n)
        println(s"$n = $p1 + $p2")
      }
    }

object P42Main extends App:
  import P42._
  printGoldbachList(9 to 20)
