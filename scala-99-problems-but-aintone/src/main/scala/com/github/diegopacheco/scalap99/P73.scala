package com.github.diegopacheco.scalap99

// P73 (**) Lisp-like tree representation.
//     There is a particular notation for multiway trees in Lisp.  Lisp is a
//     prominent functional programming language.  In Lisp almost everything is
//     a list.
//
//     Our example tree would be represented in Lisp as (a (f g) c (b d e)).
//
//     Note that in the "lispy" notation a node with successors (children)in the
//     tree is always the first element in a list, followed by its children.
//     The "lispy" representation of a multiway tree is a sequence of atoms and
//     parentheses '(' and ')', with the atoms separated by spaces.  We can
//     represent this syntax as a Scala String.  Write a method lispyTree which
//     constructs a "lispy string" from an MTree.
//
//     scala> MTree("a", List(MTree("b", List(MTree("c"))))).lispyTree
//     res0: String = (a (b c))
//
//     As a second, even more interesting, exercise try to write a method that
//     takes a "lispy" list and turns it into a multiway tree.
//
//     [Note: This is certainly one way of looking at Lisp notation, but it's
//      not how the language actually represents that syntax internally.  I can
//      elaborate more on this, if requested.  <PMG>]

object P73 {
  case class MTree[+T](value: T, children: List[MTree[T]]) {
    def lispyTree: String =
      if (children == Nil) value.toString
      else "(" + value.toString + " " + children.map(_.lispyTree).mkString(" ") + ")"
  }

  object MTree {
    def fromLispyString(s: String): MTree[String] = {
      def setNesting(nesting: Int, c: Char): Int = c match {
        case '(' => nesting + 1
        case ')' => nesting - 1
        case _ => nesting
      }

      def nextSpace(pos: Int, nesting: Int): Int =
        if ((s(pos) == ' ' || s(pos) == ')') && nesting == 0) pos
        else nextSpace(pos + 1, setNesting(nesting, s(pos)))

      def nextNonSpace(pos: Int): Int =
        if (s(pos) == ' ') nextNonSpace(pos + 1)
        else pos

      def listSubstrings(pos: Int): List[String] =
        if (pos > s.length || s(pos) == ')') Nil
        else {
          val end = nextSpace(pos, 0)
          s.substring(pos, end) :: (if (s(end) == ')') Nil else listSubstrings(nextNonSpace(end)))
        }

      if (s(0) != '(') MTree(s, Nil)
      else {
        val vEnd = nextSpace(1, 0)
        MTree(s.substring(1, vEnd), listSubstrings(nextNonSpace(vEnd)).map(fromLispyString(_)))
      }
    }
  }
}

object P73Main extends App {
  import P73._
  val tree = MTree("a", List(MTree("b", List(MTree("c", Nil)))))
  println(tree.lispyTree)
  println(MTree.fromLispyString("(a (b c))").lispyTree)
}
