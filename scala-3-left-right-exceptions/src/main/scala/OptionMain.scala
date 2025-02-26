/**
 *
 * Program result is:
 *
 * Exception in thread "main" java.lang.ExceptionInInitializerError
 * at OptionMain.main(OptionMain.scala)
 * Caused by: java.lang.RuntimeException: Error! Oopsy Dayzes.
 * at OptionMain$.maybeWorks(OptionMain.scala:15)
 * at OptionMain$.process(OptionMain.scala:21)
 * at OptionMain$.<clinit>(OptionMain.scala:25)
 * ... 1 more
 */
object OptionMain extends App {

  def maybeWorks(): Int = {
    def res = Math.random() * 50
    if (res <= 40) {
      throw new RuntimeException("Error! Oopsy Dayzes.")
    }
    42
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

