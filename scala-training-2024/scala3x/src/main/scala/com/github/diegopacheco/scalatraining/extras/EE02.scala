package com.github.diegopacheco.scalatraining.extras

/*
 * Prime Numbers: Write a function to find all prime numbers up to a given number.
 */
object EE02:
  def isPrime(n: Int): Boolean =
    n > 1 && (2 to math.sqrt(n).toInt).forall(n % _ != 0)

  def primeNumbers(n: Int): List[Int] =
    (2 to n).filter(isPrime).toList

object EE02App extends App:
  import EE02.*
  println(primeNumbers(10)) // List(2, 3, 5, 7)