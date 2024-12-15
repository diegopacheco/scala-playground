package com.github.diegopacheco.scalaplayground

import scala.compiletime.uninitialized

object Counter {
  var count:Int = uninitialized
  var name:String = uninitialized
}

object UninitializedApp extends App{

  private val c = Counter
  println(c.count) // 0
  println(c.name)  // null

}