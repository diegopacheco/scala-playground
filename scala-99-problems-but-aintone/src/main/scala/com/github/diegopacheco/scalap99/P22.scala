package com.github.diegopacheco.scalap99

// P22 (*) Create a list containing all integers within a given range.
//     Example:
//     scala> range(4, 9)
//     res0: List[Int] = List(4, 5, 6, 7, 8, 9)
object P22:
  def rangeScala(start: Int, end: Int): List[Int] =
    List.range(start, end + 1)

  def range(start: Int, end: Int):List[Int] =
    if (start > end) return Nil
    start :: range(start+1,end)

  def rangeFold[A,B](s:B)(f:(B,B)=>B)(start: Int, end: Int):List[Int] =
    if (start > end) return Nil
    start :: rangeFold(s)(f)(start+1,end)

object P22Main extends App:
  println(P22.rangeScala(4, 9))
  println(P22.range(4,9))
  println(P22.rangeFold(0)((a,b) => a+b)(4,9))