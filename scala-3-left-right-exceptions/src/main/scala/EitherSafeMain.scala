
/**
 * Still not safe with Exceptions but won't return null will be Empty String
 *
 * Result:
 *
 * Failure: Empty String
 */
object EitherSafeMain extends App {
  def maybeWorks(): String = {
    var str:String = "ok"
    str = null
    str
  }

  def process(): Either[String,Exception] = {
    val res = maybeWorks()
    // Either(res)
    //  ^ value Either in package scala does not take parameters
    // So I created Safe Either :-) Even works with Exceptions

    if (res == null) Left("Empty String") else Right(new Exception("Some Exception"))
  }

  process() match {
    case Left(value) => println(s"Failure: $value")
    case Right(value) => println(s"Success: $value")
  }
}
