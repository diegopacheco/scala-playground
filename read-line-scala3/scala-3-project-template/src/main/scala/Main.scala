import scala.io.StdIn.readLine

@main def hello(): Unit =
  println("What's up - who are you:")
  val name = readLine()
  println("Greetings from Scala3, " + name + "! Have a good day.")