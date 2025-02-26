import scala.util.Try
import scala.util.{Success,Failure}

/**
 *
 * Program result is:
 *
 *  Failure: java.lang.RuntimeException: Error! Oopsy Dayzes.
 */
object MainTry extends App {

  def maybeWorks(): Int = {
    def res = Math.random() * 50
    if (res <= 40) {
      throw new RuntimeException("Error! Oopsy Dayzes.")
    }
    42
  }

  def process(): Try[Int] = {
    val res = Try(maybeWorks())
    res
  }

  process() match {
    case Success(res) => println(s"Success: $res")
    case Failure(ex) => println(s"Failure: $ex")
  }
}


