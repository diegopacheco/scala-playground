package com.github.diegopacheco.scalap99

import scala.annotation.tailrec

// P50 (***) Huffman code.
//     First of all, consult a good book on discrete mathematics or algorithms
//     for a detailed description of Huffman codes!
//
//     We suppose a set of symbols with their frequencies, given as a list of
//     (S, F) Tuples.  E.g. (("a", 45), ("b", 13), ("c", 12), ("d", 16),
//     ("e", 9), ("f", 5)).  Our objective is to construct a list of (S, C)
//     Tuples, where C is the Huffman code word for the symbol S.
//
//     scala> huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
//     res0: List[String, String] = List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))
//
//  We'll do this functionally, with the two-queue algorithm.  (Scala's priority queues are mutable.)
//
object P50:
  def huffman[T](list: List[(T, Int)]): List[(T, String)] = {
    @tailrec
    def huffmanR(list: List[(T, Int)], acc: List[(T, String)]): List[(T, String)] = {
      if (list.size == 1) acc
      else {
        val (l1, l2) = list.sortBy(_._2).splitAt(2)
        val (s1, c1) = l1.head
        val (s2, c2) = l1.tail.head
        val newElem = (s1, "0") :: (s2, "1") :: Nil
        val combinedElem = (s1, c1 + c2)
        val newList = combinedElem :: l2
        val newAcc = acc.map { case (s, code) =>
          if (s == s1) (s, "0" + code)
          else if (s == s2) (s, "1" + code)
          else (s, code)
        } ::: newElem
        huffmanR(newList, newAcc)
      }
    }

    huffmanR(list, list.map { case (s, _) => (s, "") })
  }

object P50Main extends App:
  import P50._
  val res = huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
  println(res)