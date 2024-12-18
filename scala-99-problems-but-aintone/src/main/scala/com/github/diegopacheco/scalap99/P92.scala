package com.github.diegopacheco.scalap99

// P92 (***) Von Koch's conjecture.
//     Several years ago I met a mathematician who was intrigued by a problem
//     for which he didn't know a solution.  His name was Von Koch, and I don't
//     know whether the problem has been solved since.  [The "I" here refers to
//     the author of the Prolog problems.  <PMG>]
//
//     d e-f      1 5-4  * *1*
//     | |        | |    6 2
//     a-b-c  ->  7-3-6  *4*3*
//     |          |      5
//     g          2      *
//
//     Anyway the puzzle goes like this: Given a tree with N nodes (and hence
//     N-1 edges), find a way to enumerate the nodes from 1 to N and,
//     accordingly, the edges from 1 to N-1 in such a way, that for each edge K
//     the difference of its node numbers is equal to K.  The conjecture is that
//     this is always possible.
//
//     For small trees the problem is easy to solve by hand.  However, for
//     larger trees, and 14 is already very large, it is extremely difficult to
//     find a solution.  And remember, we don't know for sure whether there is
//     always a solution!
//
//     Write a function that calculates a numbering scheme for a given tree.
//     What is the solution for the larger tree pictured below?
//
//     i g d-k   p
//      \| |     |
//       a-c-e-q-b
//      /| |   |
//     h b f   m
//
// not working - has bugs - will fix later.
//
object P92 {
  case class Node(name: String, children: List[Node] = Nil)

  def vonKochConjecture(tree: Node): Option[Map[String, Int]] = {
    val nodes = collectNodes(tree)
    val n = nodes.size
    val edges = collectEdges(tree)

    def isValidNumbering(numbering: Map[String, Int]): Boolean = {
      edges.forall { case (a, b) =>
        val diff = (numbering(a) - numbering(b)).abs
        diff >= 1 && diff <= n - 1
      }
    }

    def findNumbering(current: Map[String, Int], remaining: List[Int]): Option[Map[String, Int]] = {
      if (remaining.isEmpty) Some(current)
      else {
        val nextNode = nodes.find(!current.contains(_)).get
        remaining.to(LazyList).flatMap { num =>
          val newNumbering = current + (nextNode -> num)
          if (isValidNumbering(newNumbering)) findNumbering(newNumbering, remaining.filter(_ != num))
          else None
        }.headOption
      }
    }

    findNumbering(Map.empty, (1 to n).toList)
  }

  private def collectNodes(tree: Node): List[String] = {
    def loop(node: Node, acc: List[String]): List[String] = {
      node.name :: node.children.flatMap(loop(_, acc))
    }

    loop(tree, Nil).distinct
  }

  private def collectEdges(tree: Node): List[(String, String)] = {
    def loop(node: Node, acc: List[(String, String)]): List[(String, String)] = {
      val edges = node.children.map(child => (node.name, child.name))
      edges ++ node.children.flatMap(loop(_, acc))
    }

    loop(tree, Nil)
  }
}

object P92Main extends App {

  import P92.*

  val tree = Node("i", List(
    Node("g", List(
      Node("d", List(
        Node("k")
      ))
    )),
    Node("p"),
    Node("a", List(
      Node("c", List(
        Node("e"),
        Node("q"),
        Node("b", List(
          Node("f")
        ))
      )),
      Node("h", List(
        Node("b")
      ))
    )),
    Node("m")
  ))

  vonKochConjecture(tree) match {
    case Some(numbering) =>
      println("Numbering:")
      numbering.foreach { case (node, num) =>
        println(s"$node -> $num")
      }
    case None => println("No solution found")
  }

}