package com.github.diegopacheco.scalap99

// P40 (**) Goldbach's conjecture.
//     Goldbach's conjecture says that every positive even number greater than 2
//     is the sum of two prime numbers.  E.g. 28 = 5 + 23.  It is one of the
//     most famous facts in number theory that has not been proved to be correct
//     in the general case.  It has been numerically confirmed up to very large
//     numbers (much larger than Scala's Int can represent).  Write a function
//     to find the two prime numbers that sum up to a given even integer.
//
//     scala> 28.goldbach
//     res0: (Int, Int) = (5,23)
object P40:
  def isPrime(n: Int): Boolean = n > 1 && (2 until n).forall(n % _ != 0)
  def primes: LazyList[Int] = 2 #:: LazyList.from(3, 2).filter(isPrime)
  def goldbachSolution(n: Int): (Int, Int) = primes.takeWhile(_ < n).find(p => isPrime(n - p)) match {
    case None     => throw new IllegalArgumentException
    case Some(p1) => (p1, n - p1)
  }
  extension (n: Int)
    def goldbach: (Int, Int) =
      if n % 2 == 0 then goldbachSolution(n) else throw new IllegalArgumentException

object P40Main extends App:
  import P40.*
  println(28.goldbach)

