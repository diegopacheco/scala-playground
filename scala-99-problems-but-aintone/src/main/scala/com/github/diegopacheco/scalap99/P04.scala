package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P04 (*) Find the number of elements of a list.
//     Example:
//     scala> length(List(1, 1, 2, 3, 5, 8))
//     res0: Int = 6
object P04:
  def lengthScala[A](list:List[A]):Int = list.length

  def lengthFold[A](list:List[A]):Int = list.foldLeft(0)((acc, _) => acc + 1)

  @tailrec
  def lengthRecursive[A](list:List[A], acc:Int = 0):Int = list match
    case Nil => acc
    case _ :: tail => lengthRecursive(tail, acc + 1)

object P04Main extends App:
  println(P04.lengthScala(List(1, 1, 2, 3, 5, 8)))
  println(P04.lengthFold(List(1, 1, 2, 3, 5, 8)))
  println(P04.lengthRecursive(List(1, 1, 2, 3, 5, 8)))