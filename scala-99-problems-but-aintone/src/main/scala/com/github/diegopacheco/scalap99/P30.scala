package com.github.diegopacheco.scalap99

// P30 - Sorting a list of lists according to length of sublists (2)
//
// scala> lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
// res0: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
// scala > lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
// res1: List[List[Symbol]]
// = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))
object P30:
  def lsort[A](ls:List[List[A]]):List[List[A]] =
    ls.sortBy(_.length)

  def lsortFreq[A](ls:List[List[A]]):List[List[A]] =
    val freq = ls.groupBy(_.length).mapValues(_.length)
    ls.sortBy(x => freq(x.length))

object P30Main extends App:
  println(P30.lsort(List(List('a', 'b', 'c'), List('d', 'e'), List('f', 'g', 'h'), List('d', 'e'), List('i', 'j', 'k', 'l'), List('m', 'n'), List('o'))) )
  println(P30.lsortFreq(List(List('a', 'b', 'c'), List('d', 'e'), List('f', 'g', 'h'), List('d', 'e'), List('i', 'j', 'k', 'l'), List('m', 'n'), List('o'))) )