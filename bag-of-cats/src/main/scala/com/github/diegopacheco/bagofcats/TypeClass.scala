package com.github.diegopacheco.bagofcats

/**
 * Type classes are a powerful tool for abstraction and extensibility in Scala.
 * They allow you to extend existing libraries with new functionality, without using inheritance.
 * Type classes goes along with linear algebra and category theory.
 */

// JSON AST
sealed trait Json {
  def value: String
}

// The "serialize to JSON" behaviour is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

final case class JsObject(get: Map[String, Json]) extends Json {
  def value: String = get.map { case (k, v) => s""""$k":${v.value}""" }.mkString("{", ",", "}")
}

final case class JsString(get: String) extends Json {
  def value: String = s""""$get""""
}

final case class JsNumber(get: Double) extends Json {
  def value: String = get.toString
}

case object JsNull extends Json {
  def value: String = "null"
}

class Person(val name: String, val email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json =
        JsString(value)
    }

  implicit val numberWriter: JsonWriter[Double] =
    new JsonWriter[Double] {
      def write(value: Double): Json =
        JsNumber(value)
    }

  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person): Json =
        JsObject(
          Map(
            "name" -> JsString(value.name),
            "email" -> JsString(value.email)
          )
        )
    }
}

@main def main = {
  import JsonWriterInstances._
  val person = new Person("John Doe", "johndoe@jd.jd")
  val json = Json.toJson(person)
  println(json.value) // {"name":"John Doe","email":"johndoe@jd.jd"}
}

