package com.github.diegopacheco.scala3x.playground.pratical

object IntersectionTypes extends App:
  trait Resettable:
    def reset(): Unit =
      println("Resetting")

  trait Growable[T]:
    def add(t: T): Unit =
      println("Growing with " + t)

  def doWork(x: Resettable & Growable[String]) =
    x.reset()
    x.add("scala 3")

  object Worker extends Resettable with Growable[String]
  doWork(Worker)
