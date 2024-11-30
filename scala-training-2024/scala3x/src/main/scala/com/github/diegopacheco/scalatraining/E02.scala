package com.github.diegopacheco.scalatraining

/*
 * Given a string as parameter tell if it's a palindrome or not
 */
object E02:
  def isPalindrome(s: String): Boolean = s == s.reverse

object E02Main extends App {
  println(E02.isPalindrome("hello")) // false
  println(E02.isPalindrome("helloolleh")) // true
}