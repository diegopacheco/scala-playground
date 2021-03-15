package com.github.diegopacheco.scala3.playground.features

object A {
  class TC
  val tc = TC()
  given TC = tc
  def f(using TC) = println(tc)
}
  
object B:
  import A._       // import all non-given members
  import A.given   // import the given instance

@main def GivenImportsMain():Unit = {
  import A._
  import A.given
  import B._
  import B.given
   
  f
}
