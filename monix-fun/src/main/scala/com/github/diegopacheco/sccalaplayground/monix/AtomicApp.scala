package com.github.diegopacheco.sccalaplayground.monix

object AtomicApp extends App {

  import monix.execution.atomic._

  val ref = Atomic(BigInt(1))

  ref.incrementAndGet()
  // res0: BigInt = 2

  ref.addAndGet(BigInt("329084291234234"))
  // res1: BigInt = 329084291234236

  println(ref.get())

}
