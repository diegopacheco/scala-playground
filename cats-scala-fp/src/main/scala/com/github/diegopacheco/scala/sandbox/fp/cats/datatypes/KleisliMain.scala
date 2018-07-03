package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/kleisli.html
 * 
 * Kleisli
 * Kleisli enables composition of functions that return a monadic value, for instance an Option[Int] or a Either[String, List[Double]],
 * without having functions take an Option or Either as a parameter, which can be strange and unwieldy.
 * 
 * We may also have several functions which depend on some environment and want a nice way to compose these functions 
 * to ensure they all receive the same environment. Or perhaps we have functions which depend on their own “local” configuration and all 
 * the configurations together make up a “global” application configuration. How do we have these functions play nice with each other
 * despite each only knowing about their own local requirements?
 * 
 * These situations are where Kleisli is immensely helpful.
 * 
 */
object KleisliMain extends App {
  
  val twice: Int => Int = x => x * 2

  val countCats: Int => String = x => if (x == 1) "1 cat" else s"$x cats"

  val twiceAsManyCats: Int => String = twice andThen countCats
  
  println(twiceAsManyCats(2))
  
  import cats.FlatMap
  import cats.implicits._
  
  final case class Kleisli[F[_], A, B](run: A => F[B]) {
  def compose[Z](k: Kleisli[F, Z, A])(implicit F: FlatMap[F]): Kleisli[F, Z, B] =
    Kleisli[F, Z, B](z => k.run(z).flatMap(run))
  }
  
  import cats.implicits._
  
  val parse: Kleisli[Option,String,Int] = Kleisli((s: String) => if (s.matches("-?[0-9]+")) Some(s.toInt) else None)

  val reciprocal: Kleisli[Option,Int,Double] = Kleisli((i: Int) => if (i != 0) Some(1.0 / i) else None)

  val parseAndReciprocal: Kleisli[Option,String,Double] = reciprocal.compose(parse)
  println(parseAndReciprocal.run("2"))
}