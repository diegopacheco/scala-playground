package com.github.diegopacheco.scalap99

// P61 (*) Count the leaves of a binary tree.
//     A leaf is a node with no successors.  Write a method leafCount to count
//     them.
//
//     scala> Node('x', Node('x'), End).leafCount
//     res0: Int = 1
object P61 {
  sealed abstract class Tree[+T]:
    def leafCount: Int

  case object End extends Tree[Nothing]:
    def leafCount: Int = 0

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]:
    def leafCount: Int = (left, right) match
      case (End, End) => 1
      case _ => left.leafCount + right.leafCount
}

object P61Main extends App {
  import P61._
  assert(1 == Node('x', Node('x', End, End), End).leafCount)
  assert(2 == Node('x', Node('x', End, End), Node('x', End, End)).leafCount)
  assert(0 == End.leafCount)
  println("All correct")
}