package com.github.diegopacheco.scalaplayground

object TypeLevelFactorial {
  import scala.compiletime.ops.int.*

  type Factorial[N <: Int] <: Int = N match
    case 0 => 1
    case _ => N * Factorial[N - 1]

  inline def factorial[N <: Int](using f: ValueOf[Factorial[N]]): Int =
    f.value
}

object FactorialApp extends App {
  import TypeLevelFactorial.*

  println(s"Factorial of 0: ${factorial[0]}")
  println(s"Factorial of 1: ${factorial[1]}")
  println(s"Factorial of 2: ${factorial[2]}")
  println(s"Factorial of 3: ${factorial[3]}")
  println(s"Factorial of 4: ${factorial[4]}")
  println(s"Factorial of 5: ${factorial[5]}")
}