package com.github.diegopacheco.scalap99

// P44 - A list of Goldbach compositions(3)
//
// Given a range of integers by its lower and upper limit, print a list of
// all even numbers and their Goldbach composition.
// scala> printGoldbachList(9 to 20)
// 10 = 3 + 7
// 12 = 5 + 7
// 14 = 3 + 11
// 16 = 3 + 13
// 18 = 5 + 13
// 20 = 3 + 17
//
// In most cases, if an even number is written as the sum of two prime numbers, one of them is very
// small.Very rarely, the primes are both bigger than, say, 50.Try to find out how many such cases
// there are in the range 2..3000.
//
// Example (minimum value of 50 for the primes):
// scala> printGoldbachListLimited(1 to 2000, 50)
// 992 = 73 + 919
// 1382 = 61 + 1321
// 1856 = 67 + 1789
// 1928 = 61 + 1867
//
object P44:
  def printGoldbachList(range: Range): Unit =
    range.filter(n => n % 2 == 0 && n > 2).foreach(n => {
      val res = P41.goldbach(n)
      println(s"${n} = ${res._1} + ${res._2}")
    })

  def printGoldbachListLimited(range: Range, min: Int): Unit =
    range.filter(n => n % 2 == 0 && n > 2).foreach(n => {
      val res = P41.goldbach(n)
      if (res._1 > min && res._2 > min)
        println(s"${n} = ${res._1} + ${res._2}")
    })

object P44Main extends App:
  P44.printGoldbachList(9 to 20)
  P44.printGoldbachListLimited(1 to 2000, 50)