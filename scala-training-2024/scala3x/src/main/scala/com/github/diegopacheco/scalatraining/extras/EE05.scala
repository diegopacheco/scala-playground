package com.github.diegopacheco.scalatraining.extras

/*
 * Count occurrences: Write a function to count the number of occurrences of occurrences of each element in a list.
 */
object EE05:
  def countOccurrences[A](list: List[A]): Map[A, Int] =
    list.groupMapReduce(identity)(_ => 1)(_ + _)

object EE05App extends App {
  val list = List(1, 2, 3, 4, 1, 2, 3, 1, 2, 1)
  println(EE05.countOccurrences(list))
}
