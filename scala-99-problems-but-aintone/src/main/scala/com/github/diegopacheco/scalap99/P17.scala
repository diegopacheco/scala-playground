package com.github.diegopacheco.scalap99

// P17 (*) Split a list into two parts.
//     The length of the first part is given. Use a Tuple for your result.
//
//     Example:
//     scala> split(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
//     res0: (List[Symbol], List[Symbol]) = (List('a', 'b', 'c'),List('d', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
object P17:
  def splitScala[A](n: Int, ls: List[A]):(List[A],List[A]) =
    ls.splitAt(n)

  def splitFP[A](n: Int, ls: List[A]): (List[A], List[A]) =
    (ls.take(n), ls.drop(n))

object P17Main extends App:
  println(P17.splitScala(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(P17.splitFP(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))