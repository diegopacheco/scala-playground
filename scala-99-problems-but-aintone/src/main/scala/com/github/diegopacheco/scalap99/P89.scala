package com.github.diegopacheco.scalap99

// P89 (**) Bipartite graphs.
//     Write a function that determines whether a given graph is bipartite.
//
//     scala> Digraph.fromString("[a>b, c>a, d>b]").isBipartite
//     res0: Boolean = true
//
//     scala> Graph.fromString("[a-b, b-c, c-a]").isBipartite
//     res1: Boolean = false
//
//     scala> Graph.fromString("[a-b, b-c, d]").isBipartite
//     res2: Boolean = true
//
//     scala> Graph.fromString("[a-b, b-c, d, e-f, f-g, g-e, h]").isBipartite
//     res3: Boolean = false
//
// Result looks wrong - it might have a bug - I will fix later.
//
object P89 {
  abstract class GraphBase[T, U: Ordering] {

    case class Node(value: T, neighbors: List[Node] = List()) {
      def degree: Int = neighbors.length

      def partners: List[Node] = neighbors
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

    def splitGraph: List[GraphBase[T, U]] = {
      import scala.collection.mutable

      val visited = mutable.Set[Node]()
      val components = mutable.ListBuffer[GraphBase[T, U]]()

      def dfs(node: Node, graph: GraphBase[T, U]): Unit = {
        if (!visited.contains(node)) {
          visited += node
          graph.addNode(node.value)
          node.neighbors.foreach { neighbor =>
            graph.addNode(neighbor.value)
            edges.find(e => (e.n1 == node && e.n2 == neighbor) || (e.n1 == neighbor && e.n2 == node)) match {
              case Some(edge) => graph.addEdge(node.value, neighbor.value, edge.value)
              case None => // Handle the case where the edge is not found
            }
            dfs(neighbor, graph)
          }
        }
      }

      nodes.values.foreach { node =>
        if (!visited.contains(node)) {
          val component = new Graph[T, U]
          dfs(node, component)
          components += component
        }
      }

      components.toList
    }

    import scala.collection.mutable.ListBuffer

    def isBipartiteR(evenToCheck: ListBuffer[Node], oddToCheck: ListBuffer[Node], evenSeen: Set[Node], oddSeen: Set[Node]): Boolean = {
      (evenToCheck, oddToCheck) match {
        case (ListBuffer(), ListBuffer()) => true
        case (e +: eTail, odd) =>
          e.partners.forall(!evenSeen(_)) && {
            evenToCheck.remove(0)
            odd ++= e.partners.filterNot(oddSeen)
            isBipartiteR(evenToCheck, odd, evenSeen + e, oddSeen ++ e.partners)
          }
        case (ListBuffer(), o +: oTail) =>
          o.partners.forall(!oddSeen(_)) && {
            oddToCheck.remove(0)
            evenToCheck ++= o.partners.filterNot(evenSeen)
            isBipartiteR(evenToCheck, oddToCheck, evenSeen ++ o.partners, oddSeen + o)
          }
      }
    }

    def isBipartiteInternal: Boolean = {
      val evenToCheck = ListBuffer[Node]()
      val oddToCheck = ListBuffer[Node]()
      nodes.values.foreach { node =>
        if (!evenToCheck.contains(node) && !oddToCheck.contains(node)) {
          evenToCheck += node
        }
      }
      isBipartiteR(evenToCheck, oddToCheck, Set(), Set())
    }

    def isBipartite: Boolean = {
      nodes.isEmpty || splitGraph.forall(_.isBipartiteInternal)
    }

  }

  class Digraph[T, U: Ordering] extends GraphBase[T, U]

  object Digraph {
    def fromString(s: String): Digraph[String, Unit] = {
      val g = new Digraph[String, Unit]
      val edgePattern = """(\w+)>(\w+)""".r
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

    def adjacentLabel[T, U: Ordering](adjacentForm: List[(T, List[(T, U)])]): Digraph[T, U] = {
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

    def adjacentLabel[T, U: Ordering](adjacentForm: List[(T, List[(T, U)])]): Graph[T, U] = {
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
  }
}

object P89Main extends App {
  import P89._
  println(Digraph.fromString("[a>b, c>a, d>b]").isBipartite) // true
  println(Graph.fromString("[a-b, b-c, d]").isBipartite) // true
  println(Graph.fromString("[a-b, b-c, c-a]").isBipartite) // false
  println(Graph.fromString("[a-b, b-c, d, e-f, f-g, g-e, h]").isBipartite) // false
}