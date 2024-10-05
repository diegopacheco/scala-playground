package com.github.diegopacheco.bagofcats

import cats.Foldable
import cats.syntax.foldable.* // for combineAll and foldMap

object CatsSyntaxFoldable extends App{

   val res0 = List(1, 2, 3).combineAll
   println(res0)

   val res1 = List(1, 2, 3).foldMap(_.toString)
   println(res1)

   val res3 = List(1, 2, 3).foldLeft(0)(_ + _)
   println(res3)

   def sumF[F[_] : Foldable](values: F[Int]): Int =
      values.foldLeft(0)(_ + _)
   println(sumF(List(1, 2, 3)))

}
