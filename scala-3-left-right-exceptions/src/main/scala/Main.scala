def maybeWorks(): Int = {
  def res = Math.random() * 50
  if (res <= 40){
    throw new RuntimeException("Error! Oopsy Dayzes.")
  }
  42
}

def process(): Either[Exception,Int] = {
    val res = maybeWorks()
    Right(res)
}

/**
 *
 * Program result is:
 *
 * Exception in thread "main" java.lang.RuntimeException: Error! Oopsy Dayzes.
 * at Main$package$.maybeWorks(Main.scala:4)
 * at Main$package$.process(Main.scala:10)
 * at Main$package$.main(Main.scala:25)
 * at main.main(Main.scala:24)
 */
@main def main():Unit = {
  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
}

