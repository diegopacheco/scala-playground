import scala.annotation.threadUnsafe

class Anwser:
   @threadUnsafe lazy val universe: Int = 42


@main def hello(): Unit =
  val res = Anwser()
  println(res.universe)