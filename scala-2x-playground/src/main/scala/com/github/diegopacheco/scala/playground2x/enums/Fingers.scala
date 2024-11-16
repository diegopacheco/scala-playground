package com.github.diegopacheco.scala.playground2x.enums

//
// It’s advisable to not add values to an enumeration after its construction, as it’s not thread-safe.
//
// Issues:
//  1. All enumerations have the same type after erasure. So, we can’t have overloaded methods, even with different enumerations as arguments:
//  2. The Scala compiler does not do an exhaustiveness check for case matches.
//  3. if we send in any other argument that is not covered by the case match, we’ll get a scala.MatchError.

object Fingers extends Enumeration {
  type Finger = Value

  // also works
  //val Thumb, Index, Middle, Ring, Little = Value

  val Thumb = Value(1, "Thumb Finger")
  val Index = Value(2, "Pointing Finger")
  val Middle = Value(3, "The Middle Finger")
  val Ring = Value(4, "Finger With The Ring")
  val Little = Value(5, "Shorty Finger")
}
