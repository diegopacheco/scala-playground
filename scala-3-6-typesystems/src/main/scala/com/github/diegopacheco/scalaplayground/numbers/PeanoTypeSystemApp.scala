package com.github.diegopacheco.scalaplayground.numbers

/*
 * Peano numbers are a way of representing the natural numbers using a simple
 * set of rules. The Peano axioms define the natural numbers in terms of sets
 * and are fundamental in the field of number theory. Peano numbers are defined
 * recursively, with Zero as the base case and Succ as the successor function.
 *
 * Giuseppe Peano was an Italian mathematician known for his work in mathematical
 * logic and the foundations of mathematics. He is best known for the Peano axioms,
 * which define the natural numbers in terms of sets and are fundamental in the field
 * of number theory. Peano's work laid the groundwork for much of modern mathematical
 * logic and set theory.
 */

sealed trait Nat
case object Zero extends Nat
case class Succ[N <: Nat](n: N) extends Nat

type Zero = Zero.type
type One = Succ[Zero]
type Two = Succ[One]
type Three = Succ[Two]
type Four = Succ[Three]
type Five = Succ[Four]
type Six = Succ[Five]
type Seven = Succ[Six]
type Eight = Succ[Seven]
type Nine = Succ[Eight]

trait ToInt[N <: Nat] {
  def toInt: Int
}

object ToInt {
  implicit val zeroToInt: ToInt[Zero.type] = new ToInt[Zero.type] {
    def toInt: Int = 0
  }

  implicit def succToInt[N <: Nat](implicit toIntN: ToInt[N]): ToInt[Succ[N]] = new ToInt[Succ[N]] {
    def toInt: Int = 1 + toIntN.toInt
  }

  def apply[N <: Nat](implicit toInt: ToInt[N]): Int = toInt.toInt
}

object PeanoTypeSystemApp extends App {
  import ToInt._

  val zero: Zero = Zero
  val one: One = Succ(zero)
  val two: Two = Succ(one)
  val three: Three = Succ(two)
  val four: Four = Succ(three)
  val five: Five = Succ(four)
  val six: Six = Succ(five)
  val seven: Seven = Succ(six)
  val eight: Eight = Succ(seven)
  val nine: Nine = Succ(eight)
  println(s"zero: $zero - one: $one - two: $two - three: $three - four: $four - five: $five - six: $six - seven: $seven - eight: $eight - nine: $nine")

  def sumToInt[A <: Nat, B <: Nat](implicit toIntA: ToInt[A], toIntB: ToInt[B]): Int = {
    val i1 = toIntA.toInt
    val i2 = toIntB.toInt
    i1 + i2
  }

  println(s"1 + 2 = ${sumToInt[One, Two]}")
  println(s"2 + 3 = ${sumToInt[Two, Three]}")
  println(s"4 + 5 = ${sumToInt[Four, Five]}")
  println("Type-level addition works!")

}