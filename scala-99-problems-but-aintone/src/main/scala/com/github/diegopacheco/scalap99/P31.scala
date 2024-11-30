package com.github.diegopacheco.scalap99

// P31 (**) Determine whether a given integer number is prime.
//     scala> 7.isPrime
//     res0: Boolean = true

// A fairly naive implementation for primality testing is simply: a number is
// prime if it is not divisible by any prime number less than or equal to its
// square root.
// Here, we use a LazyList to create a lazy infinite list of prime numbers.  The
// mutual recursion between `primes` and `isPrime` works because of the limit
// on `isPrime` to the square root of the number being tested.
object P31:
  private val primes = LazyList.cons(2, LazyList.from(3, 2).filter(isPrime))

  def isPrime(start: Int): Boolean =
    (start > 1) && (primes.takeWhile {
      _ <= Math.sqrt(start)
    }.forall {
      start % _ != 0
    })

object P31Main extends App:
  println(P31.isPrime(7))
  println(P31.isPrime(29))
  println(P31.isPrime(97))
  println(P31.isPrime(6))
  println(P31.isPrime(60))
  println(P31.isPrime(96))
