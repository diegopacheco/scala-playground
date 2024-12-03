package com.github.diegopacheco.scalap99

// P65 (**) Layout a binary tree (2).
//     An alternative layout method is depicted in the illustration opposite.
//     Find out the rules and write the corresponding method.  Hint: On a given
//     level, the horizontal distance between neighboring nodes is constant.
//
//     Use the same conventions as in problem P64.
//
//     scala> Node('a', Node('b', End, Node('c')), Node('d')).layoutBinaryTree2
//     res0: PositionedNode[Char] = T[3,1]('a T[1,2]('b . T[2,3]('c . .)) T[5,2]('d . .))
//
// The layout rules for a node v with parent u and depth d are as follows:
// * x(v) is x(u) plus or minus 2^(m-d), where m is the maximum depth of the
//   tree.  The leftmost node has x(v) == 1.
// * y(v) == d
object P65 {
  sealed abstract class Tree[+T] {
    def treeDepth: Int
    def leftmostNodeDepth: Int
    def layoutBinaryTree2: Tree[T] = {
      val d = treeDepth
      val x0 = (2 to leftmostNodeDepth).map((n) => Math.pow(2, d - n).toInt).sum + 1
      layoutBinaryTree2Internal(x0, 1, d - 2)
    }

    def layoutBinaryTree2Internal(x: Int, depth: Int, exp: Int): Tree[T]
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def treeDepth: Int = (left.treeDepth max right.treeDepth) + 1
    def leftmostNodeDepth: Int = left.leftmostNodeDepth + 1
    def layoutBinaryTree2Internal(x: Int, depth: Int, exp: Int): Tree[T] =
      PositionedNode(
        value,
        left.layoutBinaryTree2Internal(x - Math.pow(2, exp).toInt, depth + 1, exp - 1),
        right.layoutBinaryTree2Internal(x + Math.pow(2, exp).toInt, depth + 1, exp - 1),
        x, depth)
  }

  class PositionedNode[+T](override val value: T,
                           override val left: Tree[T],
                           override val right: Tree[T], val x: Int, val y: Int)
    extends Node[T](value, left, right) {
    override def toString: String = "T[" + x.toString + "," + y.toString + "](" + value.toString + " " + left.toString + " " + right.toString + ")"
  }

  case object End extends Tree[Nothing] {
    def treeDepth: Int = 0
    def leftmostNodeDepth: Int = 0
    def layoutBinaryTree2Internal(x: Int, depth: Int, exp: Int) = End
  }
}

object P65Main extends App {
  import P65._
  println(Node("a", Node("b", End, Node("c", End, End)), Node("d", End, End)).layoutBinaryTree2)
}