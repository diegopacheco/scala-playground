package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P19 (**) Rotate a list N places to the left.
//     Examples:
//     scala> rotate(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
//     res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
//
//     scala> rotate(-2, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
//     res1: List[Symbol] = List('j, 'k, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
object P19:
  @tailrec
  def rotate[T](n: Int, list: List[T]): List[T] =
    val nBounded = if (list.isEmpty) 0 else n % list.length
    if (nBounded < 0) rotate(nBounded + list.length, list)
    else (list drop nBounded) ::: (list take nBounded)

object P19Main extends App:
    println(P19.rotate(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
    println(P19.rotate(-2, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))