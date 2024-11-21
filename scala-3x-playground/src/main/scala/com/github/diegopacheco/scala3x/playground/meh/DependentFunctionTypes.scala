package com.github.diegopacheco.scala3x.playground.meh

//
// Dependent Function Types
//
// Dependent function types in Scala 3 allow you to define functions
// where the return type depends on the argument value. This means that the
// type of the result can vary based on the input provided to the function.
// This feature is useful for creating more flexible and type-safe APIs.
//
//
trait Entry {
  type Key;

  val key: Key
}

object Entry {
  def extractKey(e: Entry): e.Key = e.key // a dependent method
  val extractor:(e: Entry) => e.Key = extractKey // a dependent function value
  //             ^^^^^^^^^^^^^^^^^^^
  //             a dependent function type
}

object DependentFunctionTypes extends App:
  import Entry.*

  class StringEntry(val key: String) extends Entry:
    type Key = String

  val entry = new StringEntry("exampleKey")
  println(extractor(entry)) // Output: exampleKey