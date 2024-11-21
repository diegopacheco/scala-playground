package com.github.diegopacheco.scala3x.playground.pratical

import scala.reflect.ClassTag

//
// https://docs.scala-lang.org/scala3/reference/other-new-features/transparent-traits.html
//
// In Scala 3, transparent traits allow you to define traits that
// are erased at runtime, meaning they do not generate any runtime overhead.
// This can be useful for defining type classes or other abstractions that
// only need to exist at compile time.
//
transparent trait Serializer[T]:
  def serialize(value: T): String

object Serializer:
  given Serializer[Int] with
    def serialize(value: Int): String = value.toString

  given Serializer[String] with
    def serialize(value: String): String = value

  given [T](using s: Serializer[T]): Serializer[List[T]] with
    def serialize(value: List[T]): String = value.map(s.serialize).mkString("[", ", ", "]")

object TransparentTraits extends App:

  def serializeValue[T](value: T)(using serializer: Serializer[T]): String =
    serializer.serialize(value)

  def printClassName[T: ClassTag](value: T): Unit =
    println(s"Runtime class of value: ${value.getClass.getName}")

  def printClassHierarchy[T](value: T): Unit =
    println(s"Class [${value.getClass.getSimpleName}] >>> hierarchy of value: ${value.getClass.getInterfaces.mkString(", ")}")

  import Serializer.given

  val intValue = 42
  val stringValue = "Hello, Scala 3!"
  val listValue = List(1, 2, 3)
  println(serializeValue(intValue)) // Output: 42
  println(serializeValue(stringValue)) // Output: Hello, Scala 3!
  println(serializeValue(listValue)) // Output: [1, 2, 3]
  printClassName(intValue) // Output: Runtime class of value: java.lang.Integer
  printClassName(stringValue) // Output: Runtime class of value: java.lang.String
  printClassName(listValue) // Output: Runtime class of value: scala.collection.immutable.$colon$colon
  printClassName(given_Serializer_Int)
  printClassName(given_Serializer_String)

  transparent trait Cool
  transparent trait Cooler
  object Wine extends Cool
  object Beer extends Cool with Cooler

  printClassName(Wine)
  printClassName(Beer)
  printClassHierarchy(Wine)
  printClassHierarchy(Beer)

  trait Boring
  object Go extends Boring
  printClassName(Go)
  printClassHierarchy(Go)
