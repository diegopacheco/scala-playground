package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P56.End.isSymmetric

// P56 (**) Symmetric binary trees.
//     Let us call a binary tree symmetric if you can draw a vertical line
//     through the root node and then the right subtree is the mirror image of
//     the left subtree.  Add an isSymmetric method to the Tree class to check
//     whether a given binary tree is symmetric.  Hint: Write an isMirrorOf
//     method first to check whether one tree is the mirror image of another.
//     We are only interested in the structure, not in the contents of the
//     nodes.
//
//     scala> Node('a', Node('b'), Node('c')).isSymmetric
//     res0: Boolean = true
object P56 {
  sealed abstract class Tree[+T] {
    def isMirrorOf[V](tree: Tree[V]): Boolean
    def isSymmetric: Boolean
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def isMirrorOf[V](tree: Tree[V]): Boolean = tree match {
      case t: Node[V] => left.isMirrorOf(t.right) && right.isMirrorOf(t.left)
      case _ => false
    }
    def isSymmetric: Boolean = left.isMirrorOf(right)
  }

  case object End extends Tree[Nothing] {
    def isMirrorOf[V](tree: Tree[V]): Boolean = tree == End
    def isSymmetric: Boolean = true
  }
}

object P56Main extends App {
  import P56.*
  println(Node('a', Node('b', End, End), Node('c', End, End)).isSymmetric)
  println(Node('a', Node('b', End, End), Node('c', Node('d', End, End), End)).isSymmetric)
}