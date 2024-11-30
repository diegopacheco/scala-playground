package com.github.diegopacheco.scalatraining2x

/*
* A) What is the last element of the list?
* B) Print only even numbers
* C) IF the number of Odd multiply by 3, and them if is even sum 2
* D) Sum all numbers that are bigger than 4 and double, each one of them, print only if number is even
* */
object E01 {
  val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val aResult:Int = list.last
  val bResult:List[Int] = list.filter( _ % 2 == 0 )
  val cResult:List[Int] = list.filter( _ % 2 != 0 ).map( _ * 2 ).filter(_ % 2 == 0).map( _ + 2)
  val dResult:Int = list.filter( _ > 4 ).map( _ * 2 ).filter( _ % 2 == 0 ).sum
}

object E01App extends App {
  import E01._
  println(s"1.A last element of list = Result: ${aResult}")
  println(s"1.B even numbers = Result: ${bResult}")
  println(s"1.C IF the number of Odd multiply by 3, and them if is even sum 2 = Result: ${cResult}")
  println(s"1.D Sum all numbers that are bigger than 4 and double, each one of them, print only if number is even = Result: ${dResult}")
}
