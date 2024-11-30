package com.github.diegopacheco.scalap99

// P35 (**) Determine the prime factors of a given positive integer.
//     Construct a flat list containing the prime factors in ascending order.
//
//     scala> 315.primeFactors
//     res0: List[Int] = List(3, 3, 5, 7)
object P35:
  import com.github.diegopacheco.scalap99.P31.isPrime
  private val primes = LazyList.cons(2, LazyList.from(3, 2).filter(isPrime))

  extension (n: Int)
    def primeFactors: List[Int] =
      def primeFactorsR(n: Int, ps: LazyList[Int]): List[Int] =
        if isPrime(n) then List(n)
        else if n % ps.head == 0 then ps.head :: primeFactorsR(n / ps.head, ps)
        else primeFactorsR(n, ps.tail)

      primeFactorsR(n, primes)

object P35Main extends App:
  import P35.*
  println(315.primeFactors)