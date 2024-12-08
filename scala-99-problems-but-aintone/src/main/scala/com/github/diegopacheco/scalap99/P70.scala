package com.github.diegopacheco.scalap99

// P70 - Tree construction from a node string
//
// We suppose that the nodes of a multiway tree contain single
// characters.In the depth-first order sequence of its nodes,
// a special character ^ has been inserted whenever, during the tree
// traversal, the move is a backtrack to the previous level.
//
// By this rule, the tree in the figure opposite is represented as:
// afg^^c^bd^e^^^
//
// Define the syntax of the string and write a function
// string2MTree to construct an MTree from a String.
// Make the function an implicit conversion from String.
// Write the reverse function, and make it the toString method of MTree.
//
// scala> MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e'))))).toString
// res0: String = afg^^c^bd^e^^^

sealed trait MTree[+T] {
  override def toString: String = P70.mTree2String(this)
}
case class MTreeNode[+T](value: T, children: List[MTree[T]]) extends MTree[T]
case object MTreeEnd extends MTree[Nothing]

object P70 {
  implicit def string2MTree(s: String): MTree[Char] = {
    def parse(s: List[Char]): (MTree[Char], List[Char]) = {
      (s.head, s.tail) match {
        case ('^', tail) => (MTreeEnd, tail)
        case (c, tail) => {
          val (children, rest) = parseList(tail)
          (MTreeNode(c, children), rest)
        }
      }
    }

    def parseList(s: List[Char]): (List[MTree[Char]], List[Char]) = {
      s match {
        case Nil => (Nil, Nil)
        case '^' :: tail => (Nil, tail)
        case _ => {
          val (tree, rest) = parse(s)
          val (trees, rest2) = parseList(rest)
          (tree :: trees, rest2)
        }
      }
    }

    parse(s.toList)._1
  }

  implicit def mTree2String[T](m: MTree[T]): String = {
    def buildString(m: MTree[T]): String = m match {
      case MTreeEnd => "^"
      case MTreeNode(v, children) => v.toString +
        children.map(buildString).mkString + "^"
    }
    buildString(m)
  }
}

object P70App extends App {
  import P70._
  val m = MTreeNode('a', List(MTreeNode('f',
    List(MTreeNode('g', Nil))), MTreeNode('c', Nil),
    MTreeNode('b', List(MTreeNode('d', Nil), MTreeNode('e', Nil)))))
  println(m.toString)
  println("afg^^c^bd^e^^^")
  assert("afg^^c^bd^e^^^" == m.toString)
}