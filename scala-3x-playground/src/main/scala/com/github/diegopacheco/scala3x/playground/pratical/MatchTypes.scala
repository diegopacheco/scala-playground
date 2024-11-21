package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/new-types/match-types.html
//
// Match Types
//
// Match types in Scala 3 allow you to define type-level computations by
// pattern matching on types. Here's a practical example using match types to
// define a type-safe JSON parser.
//
object MatchTypes extends App:
  type LeafElem[X] = X match
    case String => Char
    case Array[t] => LeafElem[t]
    case Iterable[t] => LeafElem[t]
    case AnyVal => X
    case AnyRef => X
    case Any => X

  val x: LeafElem[String] = 'a'
  val y: LeafElem[Array[Array[Int]]] = 1

  println(x)
  println(y)
