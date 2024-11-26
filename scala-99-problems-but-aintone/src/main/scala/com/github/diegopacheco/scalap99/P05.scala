package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P05 (*) Reverse a list.
//     Example:
//     scala> reverse(List(1, 1, 2, 3, 5, 8))
//     res0: List[Int] = List(8, 5, 3, 2, 1, 1)
object P05:
  def reverseScala[A](list: List[A]): List[A] = list.reverse

  def reversePureFP[A](ls: List[A]): List[A] =
     ls.foldLeft(List[A]()) { (r, h) => h :: r }

  @tailrec
  def reverseRecursive[A](ls: List[A], acc: List[A] = Nil): List[A] = ls match
    case Nil       => acc
    case h :: tail => reverseRecursive(tail, h :: acc)

object P05Main extends App:
  import P05._
  println(reverseScala(List(1, 1, 2, 3, 5, 8)))
  println(reversePureFP(List(1, 1, 2, 3, 5, 8)))
  println(reverseRecursive(List(1, 1, 2, 3, 5, 8)))