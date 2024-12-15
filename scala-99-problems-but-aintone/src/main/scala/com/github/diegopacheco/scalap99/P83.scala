package com.github.diegopacheco.scalap99

// P83 (**) Construct all spanning trees.
//     Write a method spanningTrees to construct all spanning trees of a given
//     graph.  With this method, find out how many spanning trees there are for
//     the graph depicted to the right.  The data of this example graph can be
//     found below.  When you have a correct solution for the spanningTrees
//     method, use it to define two other useful methods: isTree and
//     isConnected.  Both are five-minute tasks!
//
//     Graph:
//     Graph.term(List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'),
//                List(('a', 'b'), ('a', 'd'), ('b', 'c'), ('b', 'e'),
//                     ('c', 'e'), ('d', 'e'), ('d', 'f'), ('d', 'g'),
//                     ('e', 'h'), ('f', 'g'), ('g', 'h')))
//
//     scala> Graph.fromString("[a-b, b-c, a-c]").spanningTrees
//     res0: List[Graph[String,Unit]] = List([a-b, b-c], [a-c, b-c], [a-b, a-c])
// Only undirected graphs have spanning trees.
//
object P83 {
  abstract class GraphBase[T, U] {
    case class Node(value: T)
    case class Edge(n1: Node, n2: Node, value: U)

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
      val edgeStrs = edges.map(e => s"${e.n1.value}-${e.n2.value}").mkString(", ")
      s"Nodes: [$nodeStrs], Edges: [$edgeStrs]"
    }
  }

  class Graph[T, U] extends GraphBase[T, U] {
    def edgeConnectsToGraph(e: Edge, nodes: List[Node]): Boolean =
      !(nodes.contains(e.n1) == nodes.contains(e.n2)) // xor

    def spanningTrees: List[Graph[T, U]] = {
      def spanningTreesR(graphEdges: List[Edge], graphNodes: List[Node], treeEdges: List[Edge]): List[Graph[T, U]] = {
        if (graphNodes.isEmpty) List(Graph.termLabel(nodes.keys.toList, treeEdges.map(e => (e.n1.value, e.n2.value))))
        else if (graphEdges.isEmpty) Nil
        else graphEdges.filter(edgeConnectsToGraph(_, graphNodes)).flatMap { ge =>
          spanningTreesR(graphEdges.filterNot(_ == ge),
            graphNodes.filterNot(n => edgeTarget(ge, n).isDefined),
            ge :: treeEdges)
        }
      }

      spanningTreesR(edges, nodes.values.toList.tail, Nil).distinct
    }

    def isTree: Boolean = spanningTrees.lengthCompare(1) == 0
    def isConnected: Boolean = spanningTrees.nonEmpty
  }

  object Graph {
    def termLabel[T, U](nodes: List[T], edges: List[(T, T)]): Graph[T, U] = {
      val g = new Graph[T, U]
      nodes.foreach(g.addNode)
      edges.foreach { case (n1, n2) => g.addEdge(n1, n2, ().asInstanceOf[U]) }
      g
    }

    def fromString(s: String): Graph[String, Unit] = {
      val g = new Graph[String, Unit]
      val edgePattern = """(\w+)-(\w+)""".r
      val nodePattern = """(\w+)""".r
      s.substring(1, s.length - 1).split(", ").foreach {
        case edgePattern(n1, n2) =>
          if (!g.nodes.contains(n1)) g.addNode(n1)
          if (!g.nodes.contains(n2)) g.addNode(n2)
          g.addEdge(n1, n2, ())
        case nodePattern(n) =>
          if (!g.nodes.contains(n)) g.addNode(n)
      }
      g
    }
  }
}

object P83Main extends App {
  import P83._

  val g = Graph.fromString("[a-b, b-c, a-c]").spanningTrees
  println(g)
}