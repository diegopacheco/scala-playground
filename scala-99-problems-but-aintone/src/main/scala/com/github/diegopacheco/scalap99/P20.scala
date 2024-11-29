package com.github.diegopacheco.scalap99

// P20 (*) Remove the Kth element from a list.
//     Return the list and the removed element in a Tuple.  Elements are
//     numbered from 0.
//
//     Example:
//     scala> removeAt(1, List('a', 'b', 'c', 'd'))
//     res0: (List[Symbol], Symbol) = (List('a', 'c', 'd'),'b')
object P20:
  def removeAt[T](n: Int, l: List[T]): (List[T],T) =
    if (n<0 || n>=l.size) throw new NoSuchElementException
    val (a,b) = l.splitAt(n)
    (a ++ b.tail,b.head)

object P20Main extends App:
  println(P20.removeAt(1, List('a', 'b', 'c', 'd')))