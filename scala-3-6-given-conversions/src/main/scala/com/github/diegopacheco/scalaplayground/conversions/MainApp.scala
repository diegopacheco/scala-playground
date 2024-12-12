package com.github.diegopacheco.scalaplayground.conversions

object MainApp extends App{
  import ConversionsOps._

  val opt: Option[Int] = Some(41)
  private val result = JavaLike.addOne(opt.toOptional).toOption
  println(result)
}

