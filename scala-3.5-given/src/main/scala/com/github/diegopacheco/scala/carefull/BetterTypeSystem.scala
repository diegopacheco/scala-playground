package com.github.diegopacheco.scala.carefull

object BetterTypeSystem extends App{

  trait Combiner[A] {
    def combine(a: A, b: A): A
  }

  object Combiner {
    def apply[A](using c: Combiner[A]): Combiner[A] = c
  }

  object CombinerInstances {
    given Combiner[Int] with
      def combine(a: Int, b: Int): Int = a + b

    given Combiner[String] with
      def combine(a: String, b: String): String = a + b
  }

  object CombinerSyntax {
    extension [A](list: List[A])
      def combineAll(using c: Combiner[A]): A =
        list.reduce(Combiner[A].combine)
  }

  import CombinerInstances.given
  import CombinerSyntax._

  val numbers = List(1, 2, 3, 4, 5)
  val result = numbers.combineAll
  println(result)

  val strings = List("a", "b", "c", "d", "e")
  val result2 = combineAll(strings)
  println(result2)

}
