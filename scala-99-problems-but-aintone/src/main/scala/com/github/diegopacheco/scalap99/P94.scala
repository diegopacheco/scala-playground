package com.github.diegopacheco.scalap99

// P94 (***) Generate K K-regular simple graphs with N N nodes.
// In a K K-regular graph all nodes have a degree of K K; i.e. the number of edges incident in
// each node is K K. How many (non-isomorphic!) 3-regular graphs with 6 nodes are
// here? See also a table of results.
//
// N = 3  K = 2   1 solution   (69 inferences)
// [1-2, 1-3, 2-3]
//
//  N = 4  K = 2   1 solution   (95 inferences)
//  [1-2, 1-3, 2-4, 3-4]
//
//  https://aperiodic.net/pip/scala/s-99/p94.txt
//
object P94 {
  case class Edge(node1: Int, node2: Int)

  object Edge {
    implicit val ordering: Ordering[Edge] = Ordering.by(e => (e.node1, e.node2))
  }

  def generateKRegularGraphs(N: Int, K: Int): List[List[Edge]] = {
    val nodes = (1 to N).toList
    val allEdges = for {
      i <- nodes
      j <- nodes if i < j
    } yield Edge(i, j)

    val allCombinations = allEdges.combinations(N * K / 2).toList
    val kRegularGraphs = allCombinations.filter(isKRegular(_, N, K))
    kRegularGraphs.distinctBy(canonicalForm)
  }

  private def isKRegular(edges: List[Edge], N: Int, K: Int): Boolean = {
    val degree = Array.fill(N + 1)(0)
    edges.foreach { case Edge(n1, n2) =>
      degree(n1) += 1
      degree(n2) += 1
    }
    degree.count(_ == K) == N
  }

  private def canonicalForm(edges: List[Edge]): List[Edge] = {
    edges.map(e => if (e.node1 < e.node2) e else Edge(e.node2, e.node1)).sorted
  }
}

object P94Main extends App {
  import P94._

  val N = 3
  val K = 2
  val graphs = generateKRegularGraphs(N, K)
  graphs.foreach { graph =>
    println(graph.map(e => s"${e.node1}-${e.node2}").mkString(", "))
  }
}
