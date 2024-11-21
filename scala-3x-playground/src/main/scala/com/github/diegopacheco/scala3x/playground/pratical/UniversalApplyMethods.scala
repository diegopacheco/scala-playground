package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/other-new-features/creator-applications.html
//
// Universal Apply Methods
//
// Scala case classes generate apply methods, so that values of case classes can be
// created using simple function application, without needing to write new.
// Scala 3 generalizes this scheme to all concrete classes
//
class FakeBuilder(s: String):
  def this() = this("")
  override def toString: String = s"FakeBuilder: $s"

object UniversalApplyMethods extends App:
  val fb = FakeBuilder("Hello")
  println(fb)

  val fb2 = FakeBuilder()
  println(fb2)