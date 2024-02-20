import scala.util.{Left, Right, Either}

def printIfRight(x:Either[String,Int]):Unit = 
   x match {
     case Right(r) => println(s"Got it! $r")
     case Left(_) => println(s"Sorry dont care")
   }

@main def hello(): Unit =
  printIfRight(Left("0"))
  printIfRight(Right(42))