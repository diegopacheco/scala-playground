import scala.util.Try
import scala.util.Success
import scala.util.Failure

def getPort:Try[Int] = Try {
  throw new RuntimeException("Sorry dont want give a port number away! Try later.")
}

@main def hello(): Unit =
  println(getPort)
  println(getPort.getOrElse(8080))
  getPort match {
    case Success(port) => println(s"Got a port $port")
    case Failure(err)  => println(s"Got No Port. Error: $err")
  }