/**
 *
 * Program result is:
 *
 * Success: 0
 */
object OptionGap extends App {

  def maybeWorks(): Int = {
    val resInt:Integer = null
    resInt
  }

  def process(): Option[Int] = {
    val res = maybeWorks()
    Some(res)
  }

  process() match {
    case Some(res) => println(s"Success: $res")
    case None => println(s"Failure: ")
  }
}

