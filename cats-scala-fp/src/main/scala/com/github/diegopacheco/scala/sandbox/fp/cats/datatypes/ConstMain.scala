package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

/**
 * https://typelevel.org/cats/datatypes/const.html
 */
object ConstMain extends App {

  import cats.Functor
  import cats.implicits._
  import cats.Id
  import cats.data.Const

  trait Lens[S, A] {
    def get(s: S): A
    def set(s: S, a: A): S = modify(s)(_ => a)
    def modify(s: S)(f: A => A): S = modifyF[Id](s)(f)
    def modifyF[F[_]: Functor](s: S)(f: A => F[A]): F[S]
  }

}