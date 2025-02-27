/**
 *
 * Program result is:
 *
 * Right Success: 0
 */
object RightGap extends App {

  def maybeWorks(): Int = {
    var resInt:Integer = Integer.valueOf("10")
    resInt = null
    resInt
  }

  def process(): Either[Exception, Int] = {
    val res = maybeWorks()
    Right(res)
  }

  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
}

