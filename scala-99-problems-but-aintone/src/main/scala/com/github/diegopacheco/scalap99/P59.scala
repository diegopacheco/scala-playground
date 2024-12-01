package com.github.diegopacheco.scalap99

// P59 (**) Construct height-balanced binary trees.
//     In a height-balanced binary tree, the following property holds for every
//     node: The height of its left subtree and the height of its right subtree
//     are almost equal, which means their difference is not greater than one.
//
//     Write a method Tree.hbalTrees to construct height-balanced binary trees
//     for a given height with a supplied value for the nodes.  The function
//     should generate all solutions.
//
//     scala> Tree.hbalTrees(3, "x")
//     res0: List[Node[String]] = List(T(x T(x T(x . .) T(x . .)) T(x T(x . .) T(x . .))), T(x T(x T(x . .) T(x . .)) T(x T(x . .) .)), ...
object P59 {
  import com.github.diegopacheco.scalap99.P57.{Node, Tree, End}

  def hbalTrees[T](h: Int, value: T): List[Node[T]] = {
    if (h < 1) List(Node(value, End, End))
    else if (h == 1) List(Node(value, Node(value, End, End), End), Node(value, End, Node(value, End, End)))
    else {
      val t1 = hbalTrees(h-1, value)
      val t2 = hbalTrees(h-2, value)
      (for {
        a <- t1
        b <- t1
      } yield Node(value, a, b)) :::
        (for {
          a <- t1
          b <- t2
        } yield Node(value, a, b)) :::
        (for {
          a <- t2
          b <- t1
        } yield Node(value, a, b))
    }
  }
}

object P59App extends App {
  import P59._
  println(hbalTrees(3, "x"))
}