package com.github.diegopacheco.scala3.playground.features

object TypeInferenceMain extends App{

  val businessName = "Montreux Jazz Café"
  println(businessName)
  
  val ten = 10
  val eleven = 11
  println(s"10+11=${ten+eleven}")

  def fac(n: Int):Int = if (n == 0)  1 else n * fac(n - 1)
  println(s"factorial 4 == ${fac(4)}")

  case class MyPair[A, B](x:A,y:B)
  val p = MyPair(1, "scala")
  println(s"MyPair ${p.x} ${p.y}")

  def id[T](x:T) = x
  val q = id(1)
  println(s"q is ${q}")

}
