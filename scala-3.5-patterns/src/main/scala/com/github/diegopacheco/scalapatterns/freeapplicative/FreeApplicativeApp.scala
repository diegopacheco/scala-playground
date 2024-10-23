package com.github.diegopacheco.scalapatterns.freeapplicative

sealed trait ValidationOp[A]
case class CheckLength(field: String, minLength: Int) extends ValidationOp[Boolean]
case class CheckNumber(field: String) extends ValidationOp[Boolean]

case class Pure[F[_], A](a: A) extends FreeApplicative[F, A]
case class Ap[F[_], A, B](ff: FreeApplicative[F, A => B], fa: FreeApplicative[F, A]) extends FreeApplicative[F, B]

sealed trait FreeApplicative[F[_], A] {
  def map[B](f: A => B): FreeApplicative[F, B] = this match {
    case Pure(a) => Pure(f(a))
    case Ap(ff, fa) => Ap(ff.map(f.compose), fa)
  }

  def ap[B](ff: FreeApplicative[F, A => B]): FreeApplicative[F, B] = (this, ff) match {
    case (Pure(a), Pure(f)) => Pure(f(a))
    case (Pure(a), Ap(f, fa)) => Ap(f.map(g => (b: A) => g(b)(a)), fa.asInstanceOf[FreeApplicative[F, A]])
    case (Ap(f, fa), Pure(a)) => Ap(f.map(g => (b: A) => g(b)(a)), fa.asInstanceOf[FreeApplicative[F, A]])
    case (Ap(f, fa), Ap(g, ga)) => Ap(f.ap(g), fa.ap(ga.asInstanceOf[FreeApplicative[F, A]]))
  }
}

object FreeApplicative {
  def lift[F[_], A](fa: F[A]): FreeApplicative[F, A] = Ap(Pure((a: A) => a), Pure(fa.asInstanceOf[A]))
}

object Validation {
  def checkLength(field: String, minLength: Int): FreeApplicative[ValidationOp, Boolean] =
    FreeApplicative.lift(CheckLength(field, minLength))
  def checkNumber(field: String): FreeApplicative[ValidationOp, Boolean] =
    FreeApplicative.lift(CheckNumber(field))
}

object ValidationInterpreter {
  def run[A](fa: FreeApplicative[ValidationOp, A]): A = fa match {
    case Pure(a) => a
    case Ap(ff, fa) => (run(ff), run(fa)) match {
      case (f, a) => f(a)
    }
  }
  def interpret(op: ValidationOp[Boolean]): Boolean = op match {
    case CheckLength(field, minLength) => field.length >= minLength
    case CheckNumber(field) => field.forall(_.isDigit)
  }
}

object FreeApplicativeApp extends App {
  val formValidation: FreeApplicative[ValidationOp, Boolean] =
    Validation.checkLength("username", 5).ap(
      Validation.checkNumber("12345").map(result => result && _)
    )

  val result = ValidationInterpreter.run(formValidation)
  println(result) // Output: true
}