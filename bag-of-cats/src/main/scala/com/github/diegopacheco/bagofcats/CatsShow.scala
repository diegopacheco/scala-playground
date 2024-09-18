package com.github.diegopacheco.bagofcats

import cats.Show
import cats.instances.int.*

object CatsShow extends App{

  val showInt:Show[Int] = Show.apply[Int]
  val tiger:String = showInt.show(42)
  println(s"Tiger lucky number is $tiger show as string")

  import cats.syntax.show._
  println(s"this is a string too: ${42.show}")

}
