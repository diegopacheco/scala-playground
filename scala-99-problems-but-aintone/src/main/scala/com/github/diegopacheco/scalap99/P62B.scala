package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P61A.{Tree, Node, End}

// P62B (*) Collect the nodes at a given level in a list.
//      A node of a binary tree is at level N if the path from the root to the
//      node has length N-1.  The root node is at level 1.  Write a method
//      atLevel to collect all nodes at a given level in a list.
//
//      scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).atLevel(2)
//      res0: List[Char] = List(b, c)
//
//      Using atLevel it is easy to construct a method levelOrder which creates
//      the level-order sequence of the nodes.  However, there are more
//      efficient ways to do that.
//
object P62B {
  def atLevel[T](t: Tree[T], level: Int): List[T] = t match {
    case End => Nil
    case Node(value, left, right) => {
      if (level == 1) List(value)
      else atLevel(left, level - 1) ::: atLevel(right, level - 1)
    }
  }
}

object P62BMain {
  def main(args: Array[String]): Unit = {
    import P62B._
    val tree = Node('a', Node('b', End, End), Node('c', Node('d', End, End), Node('e', End, End)))
    val res0 = atLevel(tree, 2)
    println(res0)
  }
}