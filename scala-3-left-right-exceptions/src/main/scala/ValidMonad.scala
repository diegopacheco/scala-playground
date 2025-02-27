sealed trait SafeEither[+E, +A]
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
}