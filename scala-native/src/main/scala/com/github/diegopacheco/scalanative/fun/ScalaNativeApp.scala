package com.github.diegopacheco.scalanative.fun

object ScalaNativeApp extends App {
  println("Hello, world!")
  
  import scala.scalanative.native._
  
  val ptr = stackalloc[CStruct2[Int, Int]]
  !ptr._1 = 10
  !ptr._2 = 20
  println(s"first ${!ptr._1}, second ${!ptr._2}")
  
}