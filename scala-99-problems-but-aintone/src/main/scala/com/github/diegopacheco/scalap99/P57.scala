package com.github.diegopacheco.scalap99

// P57 (**) Binary search trees (dictionaries).
//     Write a function to add an element to a binary search tree.
//
//     scala> End.addValue(2)
//     res0: Node[Int] = T(2 . .)
//
//     scala> res0.addValue(3)
//     res1: Node[Int] = T(2 . T(3 . .))
//
//     scala> res1.addValue(0)
//     res2: Node[Int] = T(2 T(0 . .) T(3 . .))
//
//     Hint: The abstract definition of addValue in Tree should be
//     `def addValue[U >: T <% Ordered[U]](x: U): Tree[U]`.  The `>: T` is
//     because addValue's parameters need to be _contravariant_ in T.
//     (Conceptually, we're adding nodes above existing nodes.  In order for the
//     subnodes to be of type T or any subtype, the upper nodes must be of type
//     T or any supertype.)  The `<% Ordered[U]` allows us to use the < operator
//     on the values in the tree.
//
//     Use that function to construct a binary tree from a list of integers.
//
//     scala> Tree.fromList(List(3, 2, 5, 7, 1))
//     res3: Node[Int] = T(3 T(2 T(1 . .) .) T(5 . T(7 . .)))
//
//     Finally, use that function to test your solution to P56.
//
//     scala> Tree.fromList(List(5, 3, 18, 1, 4, 12, 21)).isSymmetric
//     res4: Boolean = true
//
//     scala> Tree.fromList(List(3, 2, 5, 7, 4)).isSymmetric
//     res5: Boolean = false

object P57 {
  sealed abstract class Tree[+T] {
    def addValue[U >: T : Ordering](x: U): Tree[U]
    def isSymmetric: Boolean
    def isMirrorOf[U >: T](tree: Tree[U]): Boolean
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def addValue[U >: T : Ordering](x: U): Tree[U] = {
      val ord = implicitly[Ordering[U]]
      if (ord.lt(x, value)) Node(value, left.addValue(x), right)
      else Node(value, left, right.addValue(x))
    }

    def isSymmetric: Boolean = left.isMirrorOf(right)

    def isMirrorOf[U >: T](tree: Tree[U]): Boolean = tree match {
      case Node(_, l, r) => left.isMirrorOf(r) && right.isMirrorOf(l)
      case End => left == End && right == End
    }
  }

  case object End extends Tree[Nothing] {
    def addValue[U : Ordering](x: U): Tree[U] = Node(x, End, End)
    def isSymmetric: Boolean = true
    def isMirrorOf[U >: Nothing](tree: Tree[U]): Boolean = tree == End
  }

  object Tree {
    def fromList[T : Ordering](l: List[T]): Tree[T] =
      l.foldLeft(End: Tree[T])((r, e) => r.addValue(e))
  }
}

object P57Main extends App {
  import P57.*
  println(Tree.fromList(List(3, 2, 5, 7, 1)))
  println(Tree.fromList(List(5, 3, 18, 1, 4, 12, 21)).isSymmetric)
  println(Tree.fromList(List(3, 2, 5, 7, 4)).isSymmetric)
}