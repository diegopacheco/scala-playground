package com.github.diegopacheco.scalap99

// P69 (**) Dotstring representation of binary trees.
//     We consider again binary trees with nodes that are identified by single
//     lower-case letters, as in the example of problem P67.  Such a tree can be
//     represented by the preorder sequence of its nodes in which dots (.) are
//     inserted where an empty subtree (End) is encountered during the tree
//     traversal.  For example, the tree shown in problem P67 is represented as
//     "abd..e..c.fg...".  First, try to establish a syntax (BNF or syntax
//     diagrams) and then write two methods, toDotstring and fromDotstring,
//     which do the conversion in both directions.
//
//     scala> Tree.string2Tree("a(b(d,e),c(,f(g,)))").toDotstring
//     res0: String = abd..e..c.fg...
//
//     scala> Tree.fromDotstring("abd..e..c.fg...")
//     res1: Node[Char] = a(b(d,e),c(,f(g,)))
//
object P69 {
  sealed abstract class Tree[+T] {
    def toDotstring: String
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T])
    extends Tree[T] {
    def toDotstring: String = value.toString +
      left.toDotstring + right.toDotstring
  }

  case object End extends Tree[Nothing] {
    def toDotstring: String = "."
  }

  object Tree {
    def fromDotstring(ds: String): Tree[Char] = {
      def fromDotstringR(pos: Int): (Tree[Char], Int) = ds(pos) match {
        case '.' => (End, pos + 1)
        case c => {
          val (lTree, lPos) = fromDotstringR(pos + 1)
          val (rTree, rPos) = fromDotstringR(lPos)
          (Node(c, lTree, rTree), rPos)
        }
      }

      fromDotstringR(0)._1
    }
  }
}

object P69Main extends App{
  import P69._
  val t = Node('a', Node('b', Node('d', End, End), Node('e', End, End)), Node('c', End, Node('f', Node('g', End, End), End)))
  println(t.toDotstring)
  println(Tree.fromDotstring("abd..e..c.fg..."))
}