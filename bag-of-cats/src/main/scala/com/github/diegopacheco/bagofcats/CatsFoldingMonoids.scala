package com.github.diegopacheco.bagofcats

import cats.Foldable
import cats.instances.int._ // for Monoid
import cats.instances.string._ // for Monoid
import cats.instances.vector._ // for Monoid

object CatsFoldingMonoids extends App{
  val res0 = Foldable[Option].nonEmpty(Option(42))
  println(res0)

  val res1 = Foldable[List].find(List(1, 2, 3))(_ % 2 == 0)
  println(res1)

  val res2 = Foldable[List].combineAll(List(1, 2, 3))
  println(res2)

  val res3 = Foldable[List].foldMap(List(1, 2, 3))(_.toString)
  println(res3)

  val ints = List(Vector(1, 2, 3), Vector(4, 5, 6))
  
  def compose[F[_]: Foldable, G[_]: Foldable, A: cats.Monoid](fa: F[G[A]]): A = {
    Foldable[F].compose[G].combineAll(fa)
  }

  val res4 = compose(ints)
  println(res4)

}
