package com.github.diegopacheco.scalatraining.extras

/*
 * Implement a binary search in scala.
 */
object EE04:
  def binarySearch(arr: Array[Int], x: Int): Int = {
    var left = 0
    var right = arr.length - 1
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (arr(mid) == x) return mid
      if (arr(mid) < x) left = mid + 1
      else right = mid - 1
    }
    -1
  }

object EE04App extends App {
  val arr = Array(2, 3, 4, 10, 40)
  val x = 10
  val result = EE04.binarySearch(arr, x)
  if (result == -1) println("Element not present")
  else println("Element found at index " + result)
}
