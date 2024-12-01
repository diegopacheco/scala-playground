package com.github.diegopacheco.scalap99

// P51 - Huffman code (1)
//
// First of all, consult a good book on discrete mathematics or algorithms for a
// detailed description of Huffman codes!
//
// We suppose a set of symbols with their frequencies, given as a
// list of (S, F) Tuples.E.g. (("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)).
// Our objective is to construct a list of (S, C) Tuples, where CC is the Huffman code word for the symbol SS.
//
// scala> huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
//  res0: List[String, String] = List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))
//
object P51{
  def huffman(list: List[(String, Int)]): List[(String, String)] = {
    case class Node(value: List[String], freq: Int, left: Option[Node], right: Option[Node])

    def buildTree(nodes: List[Node]): Node = {
      if (nodes.size == 1) nodes.head
      else {
        val sorted = nodes.sortBy(_.freq)
        val (a, b) = (sorted.head, sorted.tail.head)
        val node = Node(a.value ++ b.value, a.freq + b.freq, Some(a), Some(b))
        buildTree(node :: sorted.drop(2))
      }
    }

    def buildCode(node: Node, prefix: String = ""): List[(String, String)] = {
      if (node.left.isEmpty && node.right.isEmpty) node.value.map((_, prefix))
      else {
        val left = buildCode(node.left.get, prefix + "0")
        val right = buildCode(node.right.get, prefix + "1")
        left ++ right
      }
    }

    val nodes = list.map { case (value, freq) => Node(List(value), freq, None, None) }
    val tree = buildTree(nodes)
    buildCode(tree).sortBy(_._1)
  }
}

object P51Main extends App:
  import P51.*
  println(huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5))))
  // expect: List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))
  // got   : List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))