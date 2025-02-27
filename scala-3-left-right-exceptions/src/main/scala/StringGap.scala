/**
 *
 * Program result is:
 *
 * Right Success: null
 */
object StringGap extends App {

  def maybeWorks(): String = {
    null
  }

  def process(): Either[Exception, String] = {
    val res = maybeWorks()
    Right(res)
  }

  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
}

