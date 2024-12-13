package com.github.diegopacheco.scalap99

// P81 (**) Path from one node to another one.
//     Write a method named findPaths to find an acyclic path from one node to
//     another in a graph.  The method should return all paths.
//
//     scala> Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]").findPaths("p", "q")
//     res0: List[List[String]] = List(List(p, q), List(p, m, q))
//
//     scala> Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]").findPaths("p", "k")
//     res1: List[List[String]] = List()
//
// Wrong - some bug - will fix later.
//
object P81 {
  abstract class GraphBase[T, U](implicit ev: T => String) {
    case class Node(value: T, adj: List[Edge])

    case class Edge(n1: Node, n2: Node, value: U) {
      override def toString: String = value match {
        case () => n1.value.toString + edgeSep + n2.value.toString
        case v => n1.value.toString + edgeSep + n2.value.toString + labelSep + v
      }
    }

    val edgeSep: String
    val labelSep: String = "/"
    var nodes: Map[T, Node] = Map()
    var edges: List[Edge] = List()

    def edgeTarget(e: Edge, n: Node): Option[Node] = {
      if (e.n1 == n) Some(e.n2)
      else if (e.n2 == n) Some(e.n1)
      else None
    }

    def findPaths(source: T, dest: T): List[List[T]] = {
      def findPathsR(curNode: Node, curPath: List[T]): List[List[T]] = {
        if (curNode.value == dest) List(curPath)
        else curNode.adj.flatMap(edgeTarget(_, curNode)).filter(n => !curPath.contains(n.value)).flatMap(n => findPathsR(n, n.value :: curPath))
      }
      findPathsR(nodes(source), List(source)).map(_.reverse)
    }

    override def toString: String = {
      val (edgeStrs, unlinkedNodes) =
        edges.foldLeft((Nil: List[String], nodes.values.toList))((r, e) => (e.toString :: r._1, r._2.filter((n) => n != e.n1 && n != e.n2)))
      "[" + (unlinkedNodes.map(_.value.toString) ::: edgeStrs).mkString(", ") + "]"
    }

    def toTermForm: (List[T], List[(T, T, U)]) =
      (nodes.keys.toList, edges.map((e) => (e.n1.value, e.n2.value, e.value)))

    def toAdjacentForm: List[(T, List[(T, U)])] =
      nodes.values.toList.map((n) => (n.value, n.adj.map((e) =>
        (edgeTarget(e, n).get.value, e.value))))
  }

  class Graph[T, U](implicit ev: T => String) extends GraphBase[T, U] {
    val edgeSep: String = "-"
  }

  class Digraph[T, U](implicit ev: T => String) extends GraphBase[T, U] {
    val edgeSep: String = ">"
  }

  abstract class GraphObjBase {
    val edgeSep: String
    val labelSep: String = "/"

    def fromStringBase[U, V](s: String)(mkGraph: => GraphBase[String, U], mkDigraph: => GraphBase[String, U])(parseEdge: String => (String, String, U)): GraphBase[String, U] = {
      assert(s(0) == '[')
      assert(s(s.length - 1) == ']')
      val tokens = s.substring(1, s.length - 1).split(", *").toList
      val nodes = tokens.flatMap(_.replaceAll("/.*", "").split("[->]")).distinct
      val edges = tokens.filter(_.matches(".*[->].*")).map(parseEdge)
      tokens.find(_.matches(".*>.*")) match {
        case None =>
          val g = mkGraph
          g.nodes = nodes.map(n => n -> g.Node(n, Nil)).toMap
          g.edges = edges.map { case (n1, n2, value) => g.Edge(g.nodes(n1), g.nodes(n2), value) }
          g
        case Some(_) =>
          val g = mkDigraph
          g.nodes = nodes.map(n => n -> g.Node(n, Nil)).toMap
          g.edges = edges.map { case (n1, n2, value) => g.Edge(g.nodes(n1), g.nodes(n2), value) }
          g
      }
    }

    def fromString(s: String): GraphBase[String, Unit] =
      fromStringBase(s)(new Graph[String, Unit], new Digraph[String, Unit]) { t =>
        val split = t.split("[->]")
        (split(0), split(1), ())
      }

    def fromStringLabel(s: String): GraphBase[String, Int] =
      fromStringBase(s)(new Graph[String, Int], new Digraph[String, Int]) { t =>
        val split = t.split("[->]")
        val split2 = split(1).split("/")
        (split(0), split2(0), split2(1).toInt)
      }
  }

  object Graph extends GraphObjBase {
    val edgeSep: String = "-"
  }

  object Digraph extends GraphObjBase {
    val edgeSep: String = ">"
  }
}

object P81Main extends App {
  import P81._
  val g = Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]")
  println(g.findPaths("p", "q"))
  println(g.findPaths("p", "k"))
}