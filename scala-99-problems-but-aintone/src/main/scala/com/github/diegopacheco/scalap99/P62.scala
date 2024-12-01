package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P62.internalList
import com.github.diegopacheco.scalap99.P61A.{Tree, Node, End}

// P62 (*) Collect the internal nodes of a binary tree in a list.
//     An internal node of a binary tree has either one or two non-empty
//     successors.  Write a method internalList to collect them in a list.
//
//     scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).internalList
//     res0: List[Char] = List(a, c)
//
object P62:
  def internalList[T](tree: Tree[T]): List[T] = tree match
    case End => List.empty
    case Node(_, End, End) => List.empty
    case Node(value, left, right) => value :: internalList(left) ++ internalList(right)

object P62main extends App:
  import P62._
  val res = internalList(Node('a', Node('b', End, End), Node('c', Node('d', End, End), Node('e', End, End))))
  println(res)