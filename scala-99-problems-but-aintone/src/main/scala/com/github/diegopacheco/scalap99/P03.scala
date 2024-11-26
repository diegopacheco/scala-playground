package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P03 (*) Find the Kth element of a list.
//     By convention, the first element in the list is element 0.
//
//     Example:
//     scala> nth(2, List(1, 1, 2, 3, 5, 8))
//     res0: Int = 2
object P03:
  def nthScala[A](l:List[A],n:Int): A = if (n>0) l(n) else throw new NoSuchElementException

  @tailrec
  def nth[A](l:List[A],n:Int): A = l match
    case Nil => throw new NoSuchElementException
    case h :: t => if (n==0) h else nth(t,n-1)

object P03Main extends App {
  import P03._
  println(nthScala(List(1, 1, 2, 3, 5, 8),3))
  println(nth(List(1, 1, 2, 3, 5, 8),3))
}