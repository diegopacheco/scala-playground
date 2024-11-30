package com.github.diegopacheco.scalap99

// P29 - Sorting a list of lists according to length of sublists (1)
// scala > lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
// res0: List[List[Symbol]]
// = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
object P29:
  def lsort[T](l:List[List[T]]):List[List[T]] =
    l.sortBy(_.size)
  def lsortFreq[T](l:List[List[T]]):List[List[T]] =
    l.groupBy(_.size).toList.sortBy(_._2.size).flatMap(_._2)

object P29Main extends App:
  val l = List(List('a', 'b', 'c'), List('d', 'e'), List('f', 'g', 'h'), List('d', 'e'), List('i', 'j', 'k', 'l'), List('m', 'n'), List('o'))
  println(P29.lsort(l))
  println(P29.lsortFreq(l))
