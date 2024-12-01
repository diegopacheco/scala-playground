package com.github.diegopacheco.scalap99

// P49 (**) Gray code.
//     An n-bit Gray code is a sequence of n-bit strings constructed according
//     to certain rules. For example,
//     n = 1: C(1) = ("0", "1").
//     n = 2: C(2) = ("00", "01", "11", "10").
//     n = 3: C(3) = ("000", "001", "011", "010", "110", "111", "101", "100").
//
//     Find out the construction rules and write a function to generate Gray
//     codes.
//
//     scala> gray(3)
//     res0 List[String] = List(000, 001, 011, 010, 110, 111, 101, 100)
//
//     See if you can use memoization to make the function more
//     efficient.
object P49:
  def gray(n: Int): List[String] =
    if (n == 0) return List("")
    val lower = gray(n - 1)
    lower.map("0" + _) ::: lower.reverse.map("1" + _)

object P49Main extends App:
  println(P49.gray(3))