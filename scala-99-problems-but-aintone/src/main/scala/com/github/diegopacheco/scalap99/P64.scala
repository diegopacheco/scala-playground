package com.github.diegopacheco.scalap99

// P64 (**) Layout a binary tree (1).
//     As a preparation for drawing a tree, a layout algorithm is required to
//     determine the position of each node in a rectangular grid.  Several
//     layout methods are conceivable, one of them is shown in the illustration
//     below.
//
//     In this layout strategy, the position of a node v is obtained by the
//     following two rules:
//     * x(v) is equal to the position of the node v in the inorder sequence
//     * y(v) is equal to the depth of the node v in the tree
//
//     In order to store the position of the nodes, we add a new class with the
//     additional information.
//
//     case class PositionedNode[+T](override val value: T, override val left: Tree[T], override val right: Tree[T], x: Int, y: Int) extends Node[T](value, left, right) {
//       override def toString = "T[" + x.toString + "," + y.toString + "](" + value.toString + " " + left.toString + " " + right.toString + ")"
//     }
//
//     Write a method layoutBinaryTree that turns a tree of normal Nodes into a
//     tree of PositionedNodes.
//
//     scala> Node('a', Node('b', End, Node('c')), Node('d')).layoutBinaryTree
//     res0: PositionedNode[Char] = T[3,1](a T[1,2](b . T[2,3](c . .)) T[4,2](d . .))
//
object P64 {
  sealed abstract class Tree[+T] {
    def layoutBinaryTree: Tree[T] = layoutBinaryTreeInternal(1, 1)._1
    def layoutBinaryTreeInternal(x: Int, depth: Int): (Tree[T], Int)
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def layoutBinaryTreeInternal(x: Int, depth: Int): (Tree[T], Int) = {
      val (leftTree, myX) = left.layoutBinaryTreeInternal(x, depth + 1)
      val (rightTree, nextX) = right.layoutBinaryTreeInternal(myX + 1, depth + 1)
      (new PositionedNode(value, leftTree, rightTree, myX, depth), nextX)
    }
  }

  class PositionedNode[+T](override val value: T,
                           override val left: Tree[T],
                           override val right: Tree[T], val x: Int, val y: Int)
    extends Node[T](value, left, right) {
    override def toString: String = "T[" + x.toString + "," + y.toString + "](" + 
                value.toString + " " + left.toString + " " + right.toString + ")"
  }

  case object End extends Tree[Nothing] {
    def layoutBinaryTreeInternal(x: Int, depth: Int): (End.type, Int) = (End, x)
  }
}

object P64main extends App {
  import P64._
  println(Node('a', Node('b', End, Node('c', End, End)), Node('d', End, End)).layoutBinaryTree)
}