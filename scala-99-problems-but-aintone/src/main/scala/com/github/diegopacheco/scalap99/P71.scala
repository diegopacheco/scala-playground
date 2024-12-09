package com.github.diegopacheco.scalap99

// P71 (*) Determine the internal path length of a tree.
//     We define the internal path length of a multiway tree as the total sum of
//     the path lengths from the root to all nodes of the tree.  By this
//     definition, the tree in the figure of problem P70 has an internal path
//     length of 9.  Write a method internalPathLength to return that sum.
//
//     scala> "afg^^c^bd^e^^^".internalPathLength
//     res0: Int = 9
//

import scala.language.implicitConversions

object P71 {
  sealed trait MTree[+T] {
    override def toString: String = P71.MTree.mTree2String(this)
  }
  case class MTreeNode[+T](value: T, children: List[MTree[T]])
    extends MTree[T]
  case object MTreeEnd extends MTree[Nothing]

  object MTree {
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

    def mTree2String[T](m: MTree[T]): String = {
      def buildString(m: MTree[T]): String = m match {
        case MTreeEnd => "^"
        case MTreeNode(v, children) => v.toString + 
          children.map(buildString).mkString + "^"
      }
      buildString(m)
    }
  }

  def internalPathLength[T](tree: MTree[T]): Int = {
    def internalPathLengthR(tree: MTree[T], depth: Int): Int = 
      tree match {
        case MTreeEnd => 0
        case MTreeNode(_, children) => depth + 
          children.map(internalPathLengthR(_, depth + 1)).sum
    }
    internalPathLengthR(tree, 0)
  }

  def internalPathLength(s: String): Int = {
    if (s.isEmpty) throw 
      new IllegalArgumentException("Input string cannot be empty")
    P71.internalPathLength(MTree.string2MTree(s))
  }
}

object P71Main extends App {
  import P71._
  println(internalPathLength("afg^^c^bd^e^^^"))
}