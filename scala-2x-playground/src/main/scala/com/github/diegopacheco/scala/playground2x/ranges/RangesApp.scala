package com.github.diegopacheco.scala.playground2x.ranges

object RangesApp extends App{

  val range = 1 to 10
  println(range)  // Output: Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val range2 = 1 to 10 by 2
  println(range2)  // Output: Range(1, 3, 5, 7, 9)

  val range3 = 1 until 10
  println(range3)  // Output: Range(1, 2, 3, 4, 5, 6, 7, 8, 9)

  val range4 = 10 to 1 by -1
  println(range4)  // Output: Range(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

  val range5 = 1 to 10
  val evenNumbers = range5.filter(_ % 2 == 0)
  println(evenNumbers)  // Output: Vector(2, 4, 6, 8, 10)

  val range6 = 1 to 5
  val squares = range6.map(x => x * x)
  println(squares)  // Output: Vector(1, 4, 9, 16, 25)

}
