package com.github.diegopacheco.scalap99

// P88 (**) Connected components.
//     Write a function that splits a graph into its connected components.
//
//     scala> Graph.fromString("[a-b, c]").splitGraph
//     res0: List[Graph[String,Unit]] = List([a-b], [c])
//
//
// Does not look right - looks like a bug - will fix it later.
//
object P88 {
  abstract class GraphBase[T, U] {
    var edges: List[Edge] = List()
    var nodes: Map[T, Node] = Map()

    case class Node(value: T, neighbors: List[Node] = List()) {
      def partners: List[Node] =
        edges.map(edgePartner(_, this)).filter(_.isDefined).map(_.get).distinct
    }

    case class Edge(n1: Node, n2: Node, value: U)

    def edgePartner(e: Edge, n: Node): Option[Node] =
      if (e.n1 == n) Some(e.n2)
      else if (e.n2 == n) Some(e.n1)
      else None

    def edgeTarget(e: Edge, n: Node): Option[Node] = edgePartner(e, n)

    def splitGraph: List[GraphBase[T, U]] = {
      def nodes2Graph(nodes: List[Node]): GraphBase[T, U] = {
        val adjacentForm = nodes.map(n => (n.value, n.neighbors.flatMap(neighbor =>
          edges.find(e => (e.n1 == n && e.n2 == neighbor) || (e.n1 == neighbor && e.n2 == n)).map(e => (neighbor.value, e.value)))))
        this match {
          case _: Graph[_, _] => Graph.adjacentLabel(adjacentForm)
          case _: Digraph[_, _] => Digraph.adjacentLabel(adjacentForm)
        }
      }

      def findConnectedNodes(candidates: List[Node], soFar: List[Node]): List[Node] =
        candidates match {
          case Nil => soFar
          case n :: tail =>
            val newNodes = n.partners.filterNot(soFar.contains)
            findConnectedNodes(tail ++ newNodes, n :: soFar)
        }

      def splitGraphR(unsplit: List[Node]): List[GraphBase[T, U]] = unsplit match {
        case Nil => Nil
        case n :: tail =>
          val connectedNodes = findConnectedNodes(List(n), Nil)
          nodes2Graph(connectedNodes) :: splitGraphR(unsplit.filterNot(connectedNodes.contains))
      }

      splitGraphR(nodes.values.toList)
    }

    override def toString: String = {
      val edgeStrings = edges.map(e => s"${e.n1.value}-${e.n2.value}")
      s"[${edgeStrings.mkString(", ")}]"
    }

  }

  class Graph[T, U] extends GraphBase[T, U] {
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
  }

  object Graph {
    def adjacentLabel[T, U](adjacentForm: List[(T, List[(T, U)])]): Graph[T, U] = {
      val g = new Graph[T, U]
      adjacentForm.foreach { case (n, neighbors) =>
        if (!g.nodes.contains(n)) g.addNode(n)
        neighbors.foreach { case (neighbor, value) =>
          if (!g.nodes.contains(neighbor)) g.addNode(neighbor)
          g.addEdge(n, neighbor, value)
        }
      }
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

  class Digraph[T, U] extends GraphBase[T, U] {
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
  }

  object Digraph {
    def adjacentLabel[T, U](adjacentForm: List[(T, List[(T, U)])]): Digraph[T, U] = {
      val g = new Digraph[T, U]
      adjacentForm.foreach { case (n, neighbors) =>
        if (!g.nodes.contains(n)) g.addNode(n)
        neighbors.foreach { case (neighbor, value) =>
          if (!g.nodes.contains(neighbor)) g.addNode(neighbor)
          g.addEdge(n, neighbor, value)
        }
      }
      g
    }
  }
}

object P88Main extends App {
  import P88._
  val g = Graph.fromString("[a-b, c, d]").splitGraph
  println(g)
}