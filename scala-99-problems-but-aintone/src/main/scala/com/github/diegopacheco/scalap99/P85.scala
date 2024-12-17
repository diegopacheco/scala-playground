package com.github.diegopacheco.scalap99

// P85 (**) Graph isomorphism.
//     Two graphs G1(N1,E1) and G2(N2,E2) are isomorphic if there is a bijection
//     f: N1 â†’ N2 such that for any nodes X,Y of N1, X and Y are adjacent if and
//     only if f(X) and f(Y) are adjacent.
//
//     Write a method that determines whether two graphs are isomorphic.
//
//     scala> Graph.fromString("[a-b]").isIsomorphicTo(Graph.fromString("[5-7]"))
//     res0: Boolean = true
//
// This problem is in NP (it's one of the few for which it is unknown whether
// it's NP-complete or not), so it gets slow very quickly.  Essentially, we
// consider every mapping from the nodes of one graph into the other, which
// is O(n!).  There are some heuristics to prune the search tree
// (isValidMapping), but that can only go so far.
//
// For a more efficient algorithm (O(n^2 log n)), see "Practical Graph
// Isomorphism" by Brendan D. McKay of Vanderbilt University.
// http://cs.anu.edu.au/~bdm/nauty/PGI/
//
object P85 {

  sealed abstract class Tree[+T]:
    def leafCount: Int

  case object End extends Tree[Nothing]:
    def leafCount: Int = 0

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]:
    def leafCount: Int = (left, right) match
      case (End, End) => 1
      case _ => left.leafCount + right.leafCount

  abstract class GraphBase[T, U] {
    case class Node(value: T, neighbors: List[Node]) {
      override def equals(obj: Any): Boolean = obj match {
        case that: Node => this.value == that.value
        case _ => false
      }
      override def hashCode(): Int = value.hashCode()
    }
    case class Edge(n1: Node, n2: Node, value: U)

    var nodes: Map[T, Node] = Map()
    var edges: List[Edge] = List()

    def addNode(value: T): Node = {
      val node = Node(value, List())
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

    def isIsomorphicTo[R, S](o: GraphBase[R, S]): Boolean = {
      def listMappings(tNodes: List[Node], oNodes: List[o.Node]) =
        tNodes.flatMap(tn => oNodes.map((tn, _)))

      def isValidMapping(iso: Map[Node, o.Node]): Boolean =
        nodes.values forall { tn =>
          (!iso.contains(tn) ||
            tn.neighbors.filter(iso.contains).forall(tnn => iso(tn).neighbors.contains(iso(tnn))))
        }

      def isValidCompleteMapping(iso: Map[Node, o.Node]): Boolean =
        nodes.values forall { tn =>
          Set(tn.neighbors.map(iso.apply): _*) == Set(iso(tn).neighbors: _*)
        }

      def isIsomorphicToR(tNodes: List[Node], oNodes: List[o.Node], iso: Map[Node, o.Node]): Boolean =
        if (tNodes.isEmpty) isValidCompleteMapping(iso)
        else listMappings(tNodes, oNodes).filter(p => isValidMapping(iso + p)) exists { p =>
          isIsomorphicToR(tNodes.filterNot(_ == p._1), oNodes.filterNot(_ == p._2), iso + p)
        }

      isIsomorphicToR(nodes.values.toList, o.nodes.values.toList, Map())
    }
  }

  class Graph[T, U: Ordering] extends GraphBase[T, U] {
    def edgeConnectsToGraph(e: GraphBase[T, U]#Edge, nodes: List[GraphBase[T, U]#Node]): Boolean =
      !(nodes.contains(e.n1) == nodes.contains(e.n2)) // xor
  }

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

object P85Main extends App {
  import P85._
  println(Graph.fromString("[a-b]").isIsomorphicTo(Graph.fromString("[5-7]")))
}