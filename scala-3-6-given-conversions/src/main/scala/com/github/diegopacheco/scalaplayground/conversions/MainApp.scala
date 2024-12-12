package com.github.diegopacheco.scalaplayground.conversions

object MainApp extends App{
  import ConversionsOps.given

  val opt: Option[Int] = Some(41)
  private val result: Option[Int] = JavaLike.addOne(opt) // 42
  println(result)

  private val opt2:Option[Int] = Some(Integer.valueOf(41))
  private val javaReally: Option[Int] = ReallyJava().addTwo(opt2) // 43
  println(javaReally)

}