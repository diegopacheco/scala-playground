package com.github.diegopacheco.scalapatterns.bifunctor

trait BiFunctor[F[_, _]] {
  def bimap[A, B, C, D](fab: F[A, B])(f: A => C, g: B => D): F[C, D]
}

case class Pair[A, B](first: A, second: B)

object Pair {
  implicit val pairBiFunctor: BiFunctor[Pair] = new BiFunctor[Pair] {
    def bimap[A, B, C, D](fab: Pair[A, B])(f: A => C, g: B => D): Pair[C, D] =
      Pair(f(fab.first), g(fab.second))
  }
}

object BiFunctorApp extends App{
  val pair = Pair(1, "one")

  val transformedPair = Pair.pairBiFunctor.bimap(pair)(_.toString, _.length)
  println(transformedPair) // Output: Pair(1, 3)
}
