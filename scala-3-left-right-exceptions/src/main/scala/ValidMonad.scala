sealed trait SafeEither[+E, +A] {
  def flatMap[EE >: E, B](f: A => SafeEither[EE, B]): SafeEither[EE, B] = this match {
    case SafeLeft(e) => SafeLeft(e)
    case SafeRight(a) => f(a)
  }
  def map[B](f: A => B): SafeEither[E, B] = this match {
    case SafeLeft(e) => SafeLeft(e)
    case SafeRight(a) => SafeRight(f(a))
  }
}
case class SafeLeft[+E](value: E) extends SafeEither[E, Nothing]
case class SafeRight[+A](value: A) extends SafeEither[Nothing, A]

object SafeEither {
  def apply[A](value: => A): SafeEither[Any, A] = {
    try {
      val result = value
      if (result == null) SafeLeft(null)
      else if (result.isInstanceOf[Exception]) SafeLeft(result)
      else SafeRight(result)
    } catch {
      case e: Throwable => SafeLeft(e)
    }
  }
}

object ValidMonadMain extends App {
  val safeEitherString = SafeEither(null)
  safeEitherString match {
    case SafeLeft(value) => println(s"LEFT safeEitherString: SafeLeft($value)")
    case SafeRight(value) => println(s"RIGHT safeEitherString: SafeRight($value)")
  }

  val safeEitherException = SafeEither(throw new Exception("Exception"))
  safeEitherException match {
    case SafeLeft(value) => println(s"LEFT safeEitherException: SafeLeft($value)")
    case SafeRight(value) => println(s"RIGHT safeEitherException: SafeRight($value)")
  }

  val safeEitherInt = SafeEither(10).flatMap(x => SafeEither(x * 2))
  safeEitherInt match {
    case SafeLeft(value) => println(s"LEFT safeEitherInt: SafeLeft($value)")
    case SafeRight(value) => println(s"RIGHT safeEitherInt: SafeRight($value)")
  }
}