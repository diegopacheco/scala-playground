/**
 *
 * Program result is:
 *
 * Success: null
 */
object OptionGapString extends App {

  def maybeWorks(): String = {
    null
  }

  def process(): Option[String] = {
    val res = maybeWorks()
    Some(res)
  }

  process() match {
    case Some(res) => println(s"Success: $res")
    case None => println(s"Failure: ")
  }
}

