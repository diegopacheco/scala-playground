package com.github.diegopacheco.bagofcats

import cats.Show
import cats.instances.int.*

/**
 * Show is a type class for types that can be converted to a human readable string.
 * Show is similar to toString, but it is not a method on the object being converted.
 */
object CatsShow extends App{

  val showInt:Show[Int] = Show.apply[Int]
  val tiger:String = showInt.show(42)
  println(s"Tiger lucky number is $tiger show as string")

  import cats.syntax.show._
  println(s"this is a string too: ${42.show}")

}
