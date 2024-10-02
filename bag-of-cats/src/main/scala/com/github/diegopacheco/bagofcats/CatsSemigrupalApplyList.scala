package com.github.diegopacheco.bagofcats

import cats.Semigroupal
import cats.instances.list._ // for Semigroupal

object CatsSemigrupalApplyList extends App {

  val res = Semigroupal[List].product(List(1, 2), List(3, 4))
  println(res)

}
