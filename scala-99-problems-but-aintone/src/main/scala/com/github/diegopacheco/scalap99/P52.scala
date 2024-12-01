package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P52 - Huffman code (2)
//
// First of all, consult a good book on discrete mathematics or algorithms
//  for a detailed description of Huffman codes!
//
//  We suppose a set of symbols with their frequencies, given as a list of (S, F) Tuples.
//  E.g. (("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)).
//  Our objective is to construct a list of (S, C) Tuples, where CC
//  is the Huffman code word for the symbol SS.
//
// scala> huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
// res0: List[String, String] = List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))
//
object P52:
  def huffman(list: List[(String, Int)]): List[(String, String)] = {
    @tailrec
    def huffmanR(list: List[(List[String], Int)], acc: Map[String, String]): Map[String, String] = {
      if (list.size == 1) acc
      else {
        val sorted = list.sortBy(_._2)
        val (a1, a2) = (sorted.head, sorted.tail.head)
        val combinedElem = (a1._1 ++ a2._1, a1._2 + a2._2)
        val newList = combinedElem :: sorted.tail.tail
        val newAcc = acc ++ a1._1.map((s: String) => (s, "0" + acc.getOrElse(s, ""))) ++ a2._1.map((s: String) => (s, "1" + acc.getOrElse(s, "")))
        huffmanR(newList, newAcc)
      }
    }

    val initialList = list.map { case (s, f) => (List(s), f) }
    val finalAcc = huffmanR(initialList, Map.empty)
    finalAcc.toList.sortBy(_._1)
  }

object P52App extends App {
  import P52.*
  println(huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5))))
}


