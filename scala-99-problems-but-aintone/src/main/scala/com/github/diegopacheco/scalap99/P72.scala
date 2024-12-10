package com.github.diegopacheco.scalap99

// P72 (*) Construct the postorder sequence of the tree nodes.
//     Write a method postorder which constructs the postorder sequence of the
//     nodes of a multiway tree.  The result should be a List.
//
//     scala> "afg^^c^bd^e^^^".postorder
//     res0: List[Char] = List(g, f, c, d, e, b, a)

object P72 {
  case class MTree[+T](value: T, children: List[MTree[T]]) {
    def postorder: List[T] = children.flatMap(_.postorder) ::: List(value)
  }

  implicit def string2MTree(s: String): MTree[Char] = {
    def parseSubtrees(i: Int): (List[MTree[Char]], Int) = {
      if (i >= s.length) (Nil, i)
      else s(i) match {
        case '^' => (Nil, i + 1)
        case _ =>
          val (children, i1) = parseTrees(i + 1)
          (MTree(s(i), children) :: Nil, i1)
      }
    }

    def parseTrees(i: Int): (List[MTree[Char]], Int) = {
      if (i >= s.length || s(i) == '^') (Nil, i + 1)
      else {
        val (subtrees, i1) = parseSubtrees(i)
        val (rest, i2) = parseTrees(i1)
        (subtrees ::: rest, i2)
      }
    }

    parseTrees(0)._1.head
  }
}

object P72Main extends App {
  import P72._
  println("afg^^c^bd^e^^^".postorder)
}