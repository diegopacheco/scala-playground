package com.github.diegopacheco.scala3.playground.features

// Same effect as having trait and object implementation - but with much less typing.
object Logarithms:
  opaque type Logarithm = Double

  object Logarithm:
    def apply(d: Double):Logarithm = math.log(d)

  extension (x: Logarithm)
    def toDouble: Double = math.exp(x)
    def + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
    def * (y: Logarithm): Logarithm = x + y

object OpaqueTypeMain extends App{
  
  import Logarithms._
  
  val l2 = Logarithm(2.0)
  val l3 = Logarithm(3.0)
  println((l2 * l3).toDouble) // prints 6.0
  println((l2 + l3).toDouble) // prints 4.999...

  // val d: Double = l2 // ERROR: Found Logarithm required Double

}
