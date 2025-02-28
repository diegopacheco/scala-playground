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

object ValidMonadMain2 extends App {
  val safeEitherString = EitherS(null)
  safeEitherString match {
    case LeftS(value) => println(s"LEFT safeEitherString: LeftS($value)")
    case RightS(value) => println(s"RIGHT safeEitherString: RightS($value)")
  }

  val safeEitherException = EitherS(throw new Exception("Exception"))
  safeEitherException match {
    case LeftS(value) => println(s"LEFT safeEitherException: LeftS($value)")
    case RightS(value) => println(s"RIGHT safeEitherException: RightS($value)")
  }

  val safeEitherInt = EitherS(10).flatMap(x => EitherS(x * 2))
  safeEitherInt match {
    case LeftS(value) => println(s"LEFT safeEitherInt: LeftS($value)")
    case RightS(value) => println(s"RIGHT safeEitherInt: RightS($value)")
  }
}