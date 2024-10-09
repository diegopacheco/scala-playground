package com.github.diegopacheco.bagofcats

import cats.Eval.catsReducibleForEval.foldMap
import cats.Foldable
import cats.instances.int.* // for Monoid
import cats.instances.string._ // for Monoid

object FoldMapFun extends App{

  val res = Foldable[Vector].foldMap(Vector(1, 2, 3))(identity)
  println(res)

  val res1 = Foldable[Vector].foldMap(Vector(1, 2, 3))(_.toString + "! ")
  println(res1)

  val res2 = Foldable[Vector].foldMap("Hello world!".toVector)(_.toString.toUpperCase)
  println(res2)

}
