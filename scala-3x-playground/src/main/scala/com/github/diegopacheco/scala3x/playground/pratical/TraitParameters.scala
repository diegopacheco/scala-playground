package com.github.diegopacheco.scala3x.playground.pratical

object TraitParameters extends App:

    trait Greet(val name: String):
      def greet: String = s"Hello $name"

    class GreetImpl(name: String) extends Greet(name)

    val greet = GreetImpl("Scala 3")
    println(greet.greet)