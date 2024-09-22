package com.github.diegopacheco.bagofcats

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._ // for Monad
import cats.instances.vector._ // for Monad

object CatsMonadsDefaultInstances extends App{

  val res1 = Monad[Option].flatMap(Option(1))(a => Option(a*2))
  println(res1)

  val res2 = Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10))
  println(res2)

  val res3 = Monad[Vector].flatMap(Vector(1, 2, 3))(a => Vector(a, a*10))
  println(res3)

}
