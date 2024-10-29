package com.github.diegopacheco.scalapatterns.profunctor

/**
 * A Profunctor is a type class that represents a bifunctor
 * that is contravariant in the first argument and covariant in the second.
 */

trait Profunctor[F[_, _]] {
  def dimap[A, B, C, D](fab: F[A, B])(f: C => A, g: B => D): F[C, D]
}

case class DataPipeline[A, B](run: A => B)

object DataPipeline {
  implicit val dataPipelineProfunctor: Profunctor[DataPipeline] = new Profunctor[DataPipeline] {
    def dimap[A, B, C, D](fab: DataPipeline[A, B])(f: C => A, g: B => D): DataPipeline[C, D] =
      DataPipeline(c => g(fab.run(f(c))))
  }
}

object ProfunctorApp extends App{
  val stringToInt: DataPipeline[String, Int] = DataPipeline(_.toInt)
  val intToBoolean: DataPipeline[Int, Boolean] = DataPipeline(_ % 2 == 0)

  val stringToBoolean: DataPipeline[String, Boolean] =
    DataPipeline.dataPipelineProfunctor.dimap(stringToInt)(identity, intToBoolean.run)

  val result = stringToBoolean.run("42")
  println(result) // Output: true
}
