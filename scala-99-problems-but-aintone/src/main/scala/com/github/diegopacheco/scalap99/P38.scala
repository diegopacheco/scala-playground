package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P31.isPrime

// P38 (*) Compare the two methods of calculating Euler's totient function.
//     Use the solutions of problems P34 and P37 to compare the algorithms.  Try
//     to calculate phi(10090) as an example.
object P38:
  val primes = LazyList.cons(2, LazyList.from(3, 2).filter(isPrime))
  def time[A](label: String)(block: => A): A = {
    val now = System.currentTimeMillis()
    val ret = block
    println(label + ": " + (System.currentTimeMillis() - now) + " ms.")
    ret
  }

object P38Main extends App:
  import P38.*

  def test(n: Int): Unit = {
    time("Preload primes") {
      primes.takeWhile { (p: Int) =>
        p <= Math.sqrt(n)
      }.force
    }
    time("P34 (" + n + ")") {
      import P34.*
      n.totient
    }
    time("P37 (" + n + ")") {
      import P37.*
      phi(n)
    }
  }

  test(10090)
  // Preload primes: 40 ms.
  //P34 (10090): 8 ms.
  //P37 (10090): 26 ms.