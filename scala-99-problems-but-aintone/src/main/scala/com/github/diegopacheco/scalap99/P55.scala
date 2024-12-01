package com.github.diegopacheco.scalap99

import com.github.diegopacheco.scalap99.P54.*

// P55 (**) Construct completely balanced binary trees.
//     In a completely balanced binary tree, the following property holds for
//     every node: The number of nodes in its left subtree and the number of
//     nodes in its right subtree are almost equal, which means their difference
//     is not greater than one.
//
//     Define an object named Tree.  Write a function Tree.cBalanced to
//     construct completely balanced binary trees for a given number of nodes.
//     The function should generate all solutions.  The function should take as
//     parameters the number of nodes and a single value to put in all of them.
//
//     scala> Tree.cBalanced(4, "x")
//     res0: List(Node[String]) = List(T(x T(x . .) T(x . T(x . .))), T(x T(x . .) T(x T(x . .) .)), ...
object P55:
  def cBalanced[T](n: Int, v: T): List[Tree[T]] =
    if (n < 1) List(End)
    else if (n % 2 == 1) {
      val subtrees = cBalanced(n / 2, v)
      for {
        l <- subtrees
        r <- subtrees
      } yield Node(v, l, r)
    } else {
      val subtrees1 = cBalanced((n-1)/2, v)
      val subtrees2 = cBalanced((n-1)/2 + 1, v)
      (for {
        l <- subtrees1
        r <- subtrees2
      } yield Node(v, l, r)) :::
      (for {
        l <- subtrees2
        r <- subtrees1
      } yield Node(v, l, r))
    }

object P55Main extends App {
  import P55._
  println(cBalanced(4, "x"))
}
