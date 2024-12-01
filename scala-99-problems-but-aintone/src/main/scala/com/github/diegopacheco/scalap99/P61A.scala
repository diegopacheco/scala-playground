package com.github.diegopacheco.scalap99

// 61A (*) Collect the leaves of a binary tree in a list.
//     A leaf is a node with no successors.  Write a method leafList to
//     collect them in a list.
//
//     scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).leafList
//     res0: List[Char] = List(b, d, e)
// Note that leafCount from P61 is no longer an abstract method.
//
object P61A {
  sealed abstract class Tree[+T] {
    def leafCount: Int = leafList.length
    def leafList: List[T]
  }
  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def leafList: List[T] = (left, right) match {
      case (End, End) => List(value)
      case _ => left.leafList ::: right.leafList
    }
  }
  case object End extends Tree[Nothing] {
    def leafList: Nil.type = Nil
  }
}

object P61AMain extends App {
  import P61A._
  assert( Node('a', Node('b', End, End), Node('c', Node('d', End, End), Node('e', End, End))).leafList == List('b', 'd', 'e') )
  println("All good!")
}
