package com.github.diegopacheco.scalap99

// P60 (**) Construct height-balanced binary trees with a given number of nodes.
//     Consider a height-balanced binary tree of height H.  What is the maximum
//     number of nodes it can contain?  Clearly, MaxN = 2H - 1.  However, what
//     is the minimum number MinN?  This question is more difficult.  Try to
//     find a recursive statement and turn it into a function minHbalNodes that
//     takes a height and returns MinN.
//
//     scala> minHbalNodes(3)
//     res0: Int = 4
//
//     On the other hand, we might ask: what is the maximum height H a
//     height-balanced binary tree with N nodes can have?  Write a maxHbalHeight
//     function.
//
//     scala> maxHbalHeight(4)
//     res1: Int = 3
//
//     Now, we can attack the main problem: construct all the height-balanced
//     binary trees with a given nuber of nodes.
//
//     scala> Tree.hbalTreesWithNodes(4, "x")
//     res2: List[Node[String]] = List(T(x T(x T(x . .) .) T(x . .)), T(x T(x . T(x . .)) T(x . .)), ...
//
//     Find out how many height-balanced trees exist for N = 15.
object P60 {

  abstract class Tree[+T] {
    def nodeCount: Int
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def nodeCount: Int = left.nodeCount + right.nodeCount + 1
  }

  case object End extends Tree[Nothing] {
    def nodeCount: Int = 0
  }

  def hbalTrees[T](h: Int, value: T): List[Node[T]] = {
    if (h < 1) List(Node(value, End, End))
    else if (h == 1) List(Node(value, Node(value, End, End), End), Node(value, End, Node(value, End, End)))
    else {
      val t1 = hbalTrees(h - 1, value)
      val t2 = hbalTrees(h - 2, value)
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

  def minHbalNodes(height: Int): Int = height match {
    case 0 => 0
    case 1 => 1
    case _ => 1 + minHbalNodes(height-1) + minHbalNodes(height-2)
  }

  def maxHbalHeight(nodes: Int): Int = LazyList.from(1).takeWhile(h => minHbalNodes(h) <= nodes).last

  def hbalTreesWithNodes[T](nodes: Int, value: T): List[Node[T]] = {
    val maxHeight = maxHbalHeight(nodes)
    (1 to maxHeight).flatMap(h => hbalTrees(h, value)).filter(_.nodeCount == nodes).toList
  }

}

object P60Main extends App {
  import P60.*
  println(minHbalNodes(3))
  println(maxHbalHeight(4))
  println(hbalTreesWithNodes(4, "x"))
}