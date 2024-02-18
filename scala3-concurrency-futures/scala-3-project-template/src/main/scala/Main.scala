import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

def longRunningAlgorithm() =
  Thread.sleep(3_000)
  42

val startTime = System.currentTimeMillis()
def delta() = System.currentTimeMillis() - startTime
def sleep(millis: Long) = Thread.sleep(millis)

@main def hello(): Unit =
  println("There we go!")

  val fut = Future(longRunningAlgorithm())
  fut.onComplete {
    case Success(value) => println(s"Got the callback, value = $value")
    case Failure(e) => e.printStackTrace
  }
  
  val result =
    for
      result <- fut
    yield
      println(s"in the 'yield': ${delta()}")

  // Sequential execution (no parallelism!)
  for
    r1 <- Future { sleep(800); 1 }
    r2 <- Future { sleep(200); 2 }
    r3 <- Future { sleep(400); 3 }
  yield
    println(r1 + r2 + r3)

  sleep(5_000) // wait 5s otherwise main thread will die and no result
  // since future is no blocking - doing this to be able to wait for the results
  // yes, there are better ways to do it :-) 

  println("Done.")