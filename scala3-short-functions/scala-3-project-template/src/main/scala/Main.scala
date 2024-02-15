@main def hello(): Unit =
  val a = 10
  val b = 20
  println(s"${a} + ${b} == ${sum(a,b)}")
  println(s"${a} + 0 == ${sum(a)}")
  println(s"5 + 5 == ${sum(b=5,a=5)}")

def sum(a: Int, b: Int = 0): Int = a + b