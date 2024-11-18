package com.github.diegopacheco.scala.playground2x.lambda

object AnonymousFunctionApp extends App {

  val ints = List(1,2,3)
  var doubledInts = ints.map(_ * 2)
  println(doubledInts)

  doubledInts = ints.map((i: Int) => i * 2)
  println(doubledInts)

  doubledInts = ints.map(i => i * 2)
  println(doubledInts)

  doubledInts = ints.map{ i => i * 2 }
  println(doubledInts)

}
