package com.github.diegopacheco.scalatraining

/*
 * Write a Fiz Buzz program in Scala 3x.
 */
object E01:
  def fizzBuzz(n: Int): String =
    if (n % 15 == 0) "FizzBuzz"
    else if (n % 3 == 0) "Fizz"
    else if (n % 5 == 0) "Buzz"
    else n.toString

@main def runFizzBuzz(): Unit =
  import E01.*
  (1 to 100).foreach(n => print(fizzBuzz(n)))