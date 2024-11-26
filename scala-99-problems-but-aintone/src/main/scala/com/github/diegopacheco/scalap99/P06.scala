package com.github.diegopacheco.scalap99

// P06 (*) Find out whether a list is a palindrome.
//     Example:
//     scala> isPalindrome(List(1, 2, 3, 2, 1))
//     res0: Boolean = true
object P06:
  def isPalindrome[A](list: List[A]): Boolean = list == list.reverse

  def isPalindromePureFP[A](list: List[A]): Boolean =
    list.foldLeft((List[A](), list)) {
      case ((acc, h :: tail), _) => (h :: acc, tail)
      case (result, _)           => result
    }._1 == list

object P06Main extends App:
  import P06._
  println(isPalindrome(List(1, 2, 3, 2, 1)))
  println(isPalindrome(List("A","B","A")))
  println(isPalindrome(List("A","B","X")))
  println(isPalindromePureFP(List("A","B","A")))
  println(isPalindromePureFP(List("A","B","X")))