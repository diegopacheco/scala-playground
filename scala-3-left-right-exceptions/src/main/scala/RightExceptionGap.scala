/**
 *
 * Program result is:
 *
 * Right Success: java.lang.Exception: I don't work
 */
object RightExceptionGap extends App {

  def maybeWorks(): Object = {
    new Exception("I don't work")
  }

  def process(): Either[Exception, Object] = {
    val res = maybeWorks()
    Right(res)
  }

  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
}

