package com.github.diegopacheco.scalap99

// P18 (**) Extract a slice from a list.
//     Given two indices, I and K, the slice is the list containing the elements
//     from and including the Ith element up to but not including the Kth
//     element of the original list.  Start counting the elements with 0.
//
//     Example:
//     scala> slice(3, 7, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
//     res0: List[Symbol] = List('d', 'e', 'f', 'g')
object P18:
  def slice[A](start: Int, end: Int, ls: List[A]): List[A] =
    ls.slice(start, end)

  def sliceFP[A](s: Int, e: Int, ls: List[A]): List[A] =
    ls.drop(s).take((e - (s.max(0))))

  def sliceFP2[A](s: Int, e: Int, ls: List[A]): List[A] =
    ls.zipWithIndex.filter{ case (elem, i) => i >= s && i < e }.map(_._1)

object P18Main extends App:
  println(P18.slice(3, 7, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(P18.sliceFP(3, 7, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(P18.sliceFP2(3, 7, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))