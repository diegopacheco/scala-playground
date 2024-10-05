package com.github.diegopacheco.bagofcats

object FoldFolding extends App {

  def show[A](list: List[A]): String =
    list.foldLeft("nil")((accum, item) => s"$item then $accum")

  val res0 = show(Nil)
  println(res0)

  val res1 = show(List(1, 2, 3))
  println(res1)

  val res2 = List(1, 2, 3).foldLeft(0)(_ + _)
  println(res2)

  val res3 = List(1, 2, 3).foldRight(0)(_ + _)
  println(res3)

  val res4 = List(1, 2, 3).foldLeft(0)(_ - _)
  println(res4)

  val res5 = List(1, 2, 3).foldRight(0)(_ - _)
  println(res5)

}
