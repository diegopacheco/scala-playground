package com.github.diegopacheco.scala.hkt

// Functor
trait Mapable[M[_]] {
  def map[A, B](m: M[A])(f: A => B): M[B]
}

object Mapable {
  extension [M[_], A, B](m: M[A])(using ma: Mapable[M])
    def map(f: A => B): M[B] = ma.map(m)(f)
}

object MapableInstances {
  given Mapable[List] with {
    def map[A, B](m: List[A])(f: A => B): List[B] = m.map(f)
  }

  given Mapable[Option] with {
    def map[A, B](m: Option[A])(f: A => B): Option[B] = m.map(f)
  }

  given Mapable[Vector] with {
    def map[A, B](m: Vector[A])(f: A => B): Vector[B] = m.map(f)
  }
}

object MapableSyntax {
  extension [M[_], A](m: M[A])(using ma: Mapable[M])
    def map[B](f: A => B): M[B] = ma.map(m)(f)
}


object TypeSystemApp extends App {

  import MapableInstances.given
  import MapableSyntax._

  def reduceMappable[M[_], A](m: M[A], f: (A, A) => A)(using ma: Mapable[M]): A = {
    m match {
      case l: List[A] => l.reduce(f)
      case v: Vector[A] => v.reduce(f)
      case o: Option[A] => o.get
      case _ => throw new UnsupportedOperationException("Unsupported type")
    }
  }

  def sum(a: Int, b: Int): Int = a + b

  val l = List(1, 2, 3)
  val l2 = l.map(_ * 2)
  println(l2)
  println(reduceMappable(l2, sum))

  val o = Option(1)
  val o2 = o.map(_ * 2)
  println(o2)
  println(reduceMappable(o2, sum))

  val v = Vector(1, 2, 3)
  val v2 = v.map(_ * 2)
  println(v2)
  println(reduceMappable(v2, sum))

}
