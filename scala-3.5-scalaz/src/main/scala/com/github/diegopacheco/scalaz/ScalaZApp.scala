package com.github.diegopacheco.scalaz

object ScalaZApp extends App{

  import scalaz._
  import Scalaz._

  val res0 = Apply[Option].apply2(some(1), some(2))((a, b) => a + b)
  println(res0)

  val res1 = Traverse[List].traverse(List(1, 2, 3))(i => some(i))
  println(res1)

}
