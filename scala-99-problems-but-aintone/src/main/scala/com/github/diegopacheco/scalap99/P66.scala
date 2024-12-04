package com.github.diegopacheco.scalap99

// P66 (***) Layout a binary tree (3).
//     Yet another layout strategy is shown in the illustration opposite.  The
//     method yields a very compact layout while maintaining a certain symmetry
//     in every node.  Find out the rules and write the corresponding method.
//     Hint: Consider the horizontal distance between a node and its successor
//     nodes.  How tight can you pack together two subtrees to construct the
//     combined binary tree?
//
//     Use the same conventions as in problem P64 and P65.  Note: This is a
//     difficult problem.  Don't give up too early!
//
//     scala> Node('a', Node('b', End, Node('c')), Node('d')).layoutBinaryTree3
//     res0: PositionedNode[Char] = T[2,1]('a T[1,2]('b . T[2,3]('c . .)) T[3,2]('d . .))

// One way of tacking this problem is to first consider the envelope bounding
// the subtree of a given node, relative to the node's own position.  Each node
// then queries its subtrees for their envelopes and then determines how much
// to shift them to maintain at least one unit of space between adjacent nodes.
// From there, actually calculating the layout is relatively easy.

// We add a `bounds` property to the tree, which is a list of pairs of integers
// giving the left and right offset from a given node for each tree level
// beneath it.  The toplevel node uses `bounds` to determine the x position of
// the root of the tree, and then each node uses its shift calculation to tell
// its subnodes their locations.
//
object P66 {
  sealed abstract class Tree[+T] {
    def bounds: List[(Int, Int)]
    def layoutBinaryTree3: P64.Tree[T] =
      layoutBinaryTree3Internal(bounds.map(_._1).min * -1 + 1, 1)
    def layoutBinaryTree3Internal(x: Int, depth: Int): P64.Tree[T]
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def bounds: List[(Int, Int)] = {
      def lowerBounds = (left.bounds, right.bounds) match {
        case (Nil, Nil) => Nil
        case (lb, Nil) => lb.map((b) => (b._1 - 1, b._2 - 1))
        case (Nil, rb) => rb.map((b) => (b._1 + 1, b._2 + 1))
        case (lb, rb) => {
          val shift = lb.zip(rb).map((e) => (e._1._2 - e._2._1) / 2 + 1).max
          lb.map(Some(_)).zipAll(rb.map(Some(_)), None, None).map {
            case (Some((a, b)), Some((c, d))) => (a - shift, d + shift)
            case (Some((a, b)), None) => (a - shift, b - shift)
            case (None, Some((c, d))) => (c + shift, d + shift)
            case (None, None) => throw new Exception
          }
        }
      }
      (0, 0) :: lowerBounds
    }

    def layoutBinaryTree3Internal(x: Int, depth: Int): P64.Tree[T] = bounds match {
      case _ :: (bl, br) :: _ => new P64.PositionedNode(
        value, left.layoutBinaryTree3Internal(x + bl, depth + 1),
        right.layoutBinaryTree3Internal(x + br, depth + 1), x, depth)
      case _ => new P64.PositionedNode(value, P64.End, P64.End, x, depth)
    }
  }

  case object End extends Tree[Nothing] {
    def bounds: List[(Int, Int)] = Nil
    def layoutBinaryTree3Internal(x: Int, depth: Int): P64.End.type = P64.End
  }
}

object P66Main extends App {
  import P66._
  println(Node('a', Node('b', End, Node('c', End, End)), Node('d', End, End)).layoutBinaryTree3)
}