trait Greeting(val name: String):
  def msg = s"How are you, $name"

class BobGreeter extends Greeting("Bob"):
  println(msg)

@main def hello(): Unit =
  val g:Greeting = BobGreeter()
  println(g.msg)