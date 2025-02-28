sealed trait EitherS[+E, +A] {
  def flatMap[EE >: E, B](f: A => EitherS[EE, B]): EitherS[EE, B] = this match {
    case LeftS(e) => LeftS(e)
    case RightS(a) => f(a)
  }
  def map[B](f: A => B): EitherS[E, B] = this match {
    case LeftS(e) => LeftS(e)
    case RightS(a) => RightS(f(a))
  }
}
case class LeftS[+E](value: E) extends EitherS[E, Nothing]
case class RightS[+A](value: A) extends EitherS[Nothing, A]

object EitherS {
  def apply[A](value: => A): EitherS[Any, A] = {
    try {
      val result = value
      if (result == null) LeftS(null)
      else if (result.isInstanceOf[Exception]) LeftS(result)
      else RightS.safe(result)
    } catch {
      case e: Throwable => LeftS(e)
    }
  }
}

object RightS {
  def apply[A](value: A): RightS[A] = new RightS(value)
  def safe[A](value: A): EitherS[Any, A] = new RightS(value)
}