package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/new-types/type-lambdas.html
//
// Type lambdas are a way to define anonymous type constructors.
// A type lambda lets one express a higher-kinded type directly, without a type definition.
//
object TypeLambdas extends App:

    // Define a type lambda for a Map with Int keys
    type MapInt = [V] =>> Map[Int, V]
    val m: MapInt[String] = Map(1 -> "one")
    println(m)

    // Define a type lambda for a function that takes two parameters
    type BiFunction[A, B] = [R] =>> (A, B) => R
    val concat: BiFunction[String, String][String] = (a, b) => a + b
    val result = concat("Hello, ", "World!")
    println(result) // Output: Hello, World!

