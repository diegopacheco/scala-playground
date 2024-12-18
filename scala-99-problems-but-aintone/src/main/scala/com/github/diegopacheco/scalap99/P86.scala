package com.github.diegopacheco.scalap99

// P86 (**) Node degree and graph coloration.
//     a) Write a method Node.degree that determines the degree of a given node.
//
//     scala> Graph.fromString("[a-b, b-c, a-c, a-d]").nodes("a").degree
//     res0: Int = 3
//
//     b) Write a method that lists all nodes of a graph sorted according to
//        decreasing degree.
//
//     scala> Graph.fromString("[a-b, b-c, a-c, a-d]").nodesByDegree
//     res1: List[Graph[String,Unit]#Node] = List(Node(a), Node(c), Node(b), Node(d))
//
//     c) Use Welsh-Powell's algorithm to paint the nodes of a graph in such a
//        way that adjacent nodes have different colors.  Make a method
//        colorNodes that returns a list of tuples, each of which contains a
//        node and an integer representing its color.
//
//     scala> Graph.fromString("[a-b, b-c, a-c, a-d]").colorNodes
//     res2: List[(Graph[String,Unit]#Node,Int)] = List((Node(a),1), (Node(b),2), (Node(c), 3), (Node(d), 2))
//
object P86 {

  sealed abstract class Tree[+T]:
    def leafCount: Int

  case object End extends Tree[Nothing]:
    def leafCount: Int = 0

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]:
    def leafCount: Int = (left, right) match
      case (End, End) => 1
      case _ => left.leafCount + right.leafCount

  abstract class GraphBase[T, U] {
    case class Node(value: T, neighbors: List[Node] = List()) {
      def degree: Int = neighbors.length
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

    def nodesByDegree: List[Node] = nodes.values.toList.sortBy(-_.degree)

    def colorNodes: List[(Node, Int)] = {
      import collection.immutable.Set
      def applyColor(color: Int, uncolored: List[Node], colored: List[(Node, Int)], adjacentNodes: Set[Node]): List[(Node, Int)] =
        uncolored match {
          case Nil => colored
          case n :: tail =>
            val newAdjacent = adjacentNodes ++ n.neighbors
            applyColor(color, tail.filterNot(newAdjacent), (n, color) :: colored, newAdjacent)
        }

      def colorNodesR(color: Int, uncolored: List[Node], colored: List[(Node, Int)]): List[(Node, Int)] =
        if (uncolored.isEmpty) colored
        else {
          val newColored = applyColor(color, uncolored, colored, Set())
          colorNodesR(color + 1, uncolored.filterNot(newColored.map(_._1).toSet), newColored)
        }

      colorNodesR(1, nodesByDegree, Nil)
    }
  }

  class Graph[T, U: Ordering] extends GraphBase[T, U]

  object Graph {
    def fromString(s: String): Graph[String, Unit] = {
      val g = new Graph[String, Unit]
      val edgePattern = """(\w+)-(\w+)""".r
      s.substring(1, s.length - 1).split(", ").foreach {
        case edgePattern(n1, n2) =>
          if (!g.nodes.contains(n1)) g.addNode(n1)
          if (!g.nodes.contains(n2)) g.addNode(n2)
          g.addEdge(n1, n2, ())
      }
      g
    }
  }
}

object P86Main extends App {
  import P86._
  val graph = Graph.fromString("[a-b, b-c, a-c, a-d]")
  println(graph.nodes("a").degree)
  println(graph.nodesByDegree)
  println(graph.colorNodes)
}