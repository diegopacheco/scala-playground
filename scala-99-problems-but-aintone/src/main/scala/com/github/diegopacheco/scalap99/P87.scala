package com.github.diegopacheco.scalap99

// P87 (**) Depth-first order graph traversal.
//     Write a method that generates a depth-first order graph traversal
//     sequence.  The starting point should be specified, and the output should
//     be a list of nodes that are reachable from this starting point (in
//     depth-first order).
//
//     scala> Graph.fromString("[a-b, b-c, e, a-c, a-d]").nodesByDepthFrom("d")
//     res0: List[String] = List(c, b, a, d)

// Node.nodesByDepth is a little inefficient.  With immutable Lists, it ends up
// rebuilding the entire list every time it adds a node.  It would be more
// efficient to build the list backwards (adding new elements to the beginning)
// and then reverse it in nodesByDepthFrom.
//
// Similarly, nodesByDepthR isn't tail recursive.  If a node has more neighbors
// than there are stack frames, this will be a problem.
//
//
// Looks wrong - need to fix it - wil ldo later.
//
object P87 {
  abstract class GraphBase[T, U] {
    case class Node(value: T, neighbors: List[Node] = List()) {
      def degree: Int = neighbors.length

      def nodesByDegree: List[Node] = nodes.values.toList.sortBy(-_.degree)

      def nodesByDepth(seen: Set[Node]): List[Node] = {
        def nodesByDepthR(stack: List[Node], visited: Set[Node]): List[Node] = stack match {
          case Nil => Nil
          case n :: tail if visited(n) => nodesByDepthR(tail, visited)
          case n :: tail =>
            n :: nodesByDepthR(n.neighbors.filterNot(visited) ++ tail, visited + n)
        }

        nodesByDepthR(List(this), seen)
      }
    }

    var nodes: Map[T, Node] = Map()
    var edges: List[Edge] = List()

    case class Edge(n1: Node, n2: Node, value: U)

    def addNode(value: T): Node = {
      val node = Node(value)
      nodes += (value -> node)
      node
    }

    def addEdge(n1: T, n2: T, value: U): Unit = {
      val node1 = nodes(n1)
      val node2 = nodes(n2)
      val edge = Edge(node1, node2, value)
      edges = edge :: edges
      nodes += (n1 -> node1.copy(neighbors = node2 :: node1.neighbors))
      nodes += (n2 -> node2.copy(neighbors = node1 :: node2.neighbors))
    }

    def nodesByDepthFrom(start: T): List[T] =
      nodes(start).nodesByDepth(Set()).map(_.value)
  }

  class Graph[T, U: Ordering] extends GraphBase[T, U]

  object Graph {
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

object P87Main extends App {
  import P87._
  val g = Graph.fromString("[a-b, b-c, e, a-c, a-d]")
  println(g.nodesByDepthFrom("d"))
}