package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P09 (**) Pack consecutive duplicates of list elements into sublist.
//     If a list contains repeated elements they should be placed in separate
//     sublist.
//
//     Example:
//     scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//     res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
object P09:
  def pack[T](ts:List[T]):List[List[T]] = {
    @tailrec
    def packR(ts:List[T], acc:List[List[T]]):List[List[T]] = ts match {
      case Nil => acc
      case h :: _ =>
        val (packed,rest) = ts.span(_ == h)
        packR(rest,acc :+ packed)
    }
    packR(ts,List())
  }

object P09Main extends App {
  import P09._
  println(pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')))
}