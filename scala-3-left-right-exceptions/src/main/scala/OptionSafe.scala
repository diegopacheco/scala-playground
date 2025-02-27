/**
 * Still not safe with Exceptions but won't return null will be Empty String
 *
 * Result:
 *
 * Failure:
 */
object OptionSafeMain extends App {
  def maybeWorks(): String = {
    var str:String = "ok"
    str = null
    str
  }

  def process(): Option[String] = {
    val res = maybeWorks()
    Option(res)
  }

  process() match {
    case Some(res) => println(s"Success: $res")
    case None => println(s"Failure: ")
  }
}
