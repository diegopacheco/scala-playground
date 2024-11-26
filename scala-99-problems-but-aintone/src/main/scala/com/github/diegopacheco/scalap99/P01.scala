package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

//
//  P01 (*) Find the last element of a list.
//     Example:
//     scala> last(List(1, 1, 2, 3, 5, 8))
//     res0: Int = 8
//
object P01:
  def lastScala[A](l:List[A]): A = l.last

  @tailrec
  def last[A](l:List[A]): A = l match
    case h :: Nil => h
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException

object P01Main extends App:
  val l = List(1, 1, 2, 3, 5, 8)
  println(P01.last(l))
  println(P01.lastScala(l))