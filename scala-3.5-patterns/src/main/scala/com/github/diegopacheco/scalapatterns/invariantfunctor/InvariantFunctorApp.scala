package com.github.diegopacheco.scalapatterns.invariantfunctor

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    def encode(value: B): String = Codec.this.encode(enc(value))
    def decode(value: String): B = dec(Codec.this.decode(value))
  }
}

object Codec {
  implicit val stringCodec: Codec[String] = new Codec[String] {
    def encode(value: String): String = value
    def decode(value: String): String = value
  }
  implicit val intCodec: Codec[Int] = new Codec[Int] {
    def encode(value: Int): String = value.toString
    def decode(value: String): Int = value.toInt
  }
}

case class Person(name: String, age: Int)

object InvariantFunctorApp extends App{
  import Codec._

  val personCodec: Codec[Person] = stringCodec.imap[Person](
    str => {
      val parts = str.split(",")
      Person(parts(0), parts(1).toInt)
    },
    person => s"${person.name},${person.age}"
  )
  val person = Person("John Doe", 30)
  val encoded = personCodec.encode(person)
  val decoded = personCodec.decode(encoded)

  println(s"Encoded: $encoded")
  println(s"Decoded: $decoded")

}
