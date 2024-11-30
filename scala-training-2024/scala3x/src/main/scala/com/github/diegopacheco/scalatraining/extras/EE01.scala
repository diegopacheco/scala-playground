package com.github.diegopacheco.scalatraining.extras

/*
 * Write a fibonacci sequence - write a function to generate the first n Fibonacci numbers.
 */
object EE01:
  def fibonacci(n: Int): List[Int] =
    @annotation.tailrec
    def fib(n: Int, prev: Int, next: Int, acc: List[Int]): List[Int] = n match {
      case 0 => acc
      case _ => fib(n - 1, next, prev + next, acc :+ next)
    }
    if (n <= 0) List()
    else fib(n - 1, 1, 1, List(1))

object EE01App extends App {
  println(EE01.fibonacci(10))
}
