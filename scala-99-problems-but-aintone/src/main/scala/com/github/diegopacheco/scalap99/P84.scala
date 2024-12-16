package com.github.diegopacheco.scalap99

// P84 (**) Construct the minimal spanning tree.
//     Write a method minimalSpanningTree to construct the minimal spanning tree
//     of a given labeled graph.  Hint: Use Prim's Algorithm.  A small
//     modification of the solution of P83 does the trick.  The data of the
//     example graph to the right can be found below.
//
//     Graph:
//     Graph.termLabel(
//       List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'),
//            List(('a', 'b', 5), ('a', 'd', 3), ('b', 'c', 2), ('b', 'e', 4),
//                 ('c', 'e', 6), ('d', 'e', 7), ('d', 'f', 4), ('d', 'g', 3),
//                 ('e', 'h', 5), ('f', 'g', 4), ('g', 'h', 1)))
//
//     scala> Graph.fromStringLabel("[a-b/1, b-c/2, a-c/3]").minimalSpanningTree
//     res0: Graph[String,Int] = [a-b/1, b-c/2]
import scala.language.postfixOps
import scala.math.Ordering.Implicits._

object P84 {
  abstract class GraphBase[T, U : Ordering] {
    case class Node(value: T)
    case class Edge(n1: Node, n2: Node, value: U) {
      def toTuple: (T, T, U) = (n1.value, n2.value, value)
    }

    var nodes: Map[T, Node] = Map()
    var edges: List[Edge] = List()

    def addNode(value: T): Node = {
      val node = Node(value)
      nodes += (value -> node)
      node
    }

    def addEdge(n1: T, n2: T, value: U): Unit = {
      val node1 = nodes(n1)
      val node2 = nodes(n2)
      edges = Edge(node1, node2, value) :: edges
    }

    def edgeTarget(edge: Edge, node: Node): Option[Node] = {
      if (edge.n1 == node) Some(edge.n2)
      else if (edge.n2 == node) Some(edge.n1)
      else None
    }

    override def toString: String = {
      val nodeStrs = nodes.keys.mkString(", ")
      val edgeStrs = edges.map(e => s"${e.n1.value}-${e.n2.value}/${e.value}").mkString(", ")
      s"Nodes: [$nodeStrs], Edges: [$edgeStrs]"
    }
  }

  class Graph[T, U : Ordering] extends GraphBase[T, U] {
    def minimalSpanningTree: Graph[T, U] = {
      def minimalSpanningTreeR(graphEdges: List[Edge], graphNodes: List[Node], treeEdges: List[Edge]): Graph[T, U] = {
        if (graphNodes.isEmpty) Graph.termLabel(nodes.keys.toList, treeEdges.map(_.toTuple))
        else {
          val nextEdge = graphEdges.filter(edgeConnectsToGraph(_, graphNodes)).reduceLeft((r, e) => if (implicitly[Ordering[U]].lt(r.value, e.value)) r else e)
          minimalSpanningTreeR(graphEdges.filterNot(_ == nextEdge),
            graphNodes.filter(edgeTarget(nextEdge, _).isEmpty),
            nextEdge :: treeEdges)
        }
      }

      minimalSpanningTreeR(edges, nodes.values.toList.tail, Nil)
    }

    def edgeConnectsToGraph(e: Edge, nodes: List[Node]): Boolean =
      !(nodes.contains(e.n1) == nodes.contains(e.n2)) // xor
  }

  object Graph {
    def termLabel[T, U : Ordering](nodes: List[T], edges: List[(T, T, U)]): Graph[T, U] = {
      val g = new Graph[T, U]
      nodes.foreach(g.addNode)
      edges.foreach { case (n1, n2, value) => g.addEdge(n1, n2, value) }
      g
    }

    def fromStringLabel(s: String): Graph[String, Int] = {
      val g = new Graph[String, Int]
      val edgePattern = """(\w+)-(\w+)/(\d+)""".r
      s.substring(1, s.length - 1).split(", ").foreach {
        case edgePattern(n1, n2, value) =>
          if (!g.nodes.contains(n1)) g.addNode(n1)
          if (!g.nodes.contains(n2)) g.addNode(n2)
          g.addEdge(n1, n2, value.toInt)
      }
      g
    }
  }
}

object P84Main extends App {
  import P84._
  val g = Graph.fromStringLabel("[a-b/1, b-c/2, a-c/3]").minimalSpanningTree
  println(g)
}