def stringMask(a: String, b: String): String =
  b + a.drop(b.length)

@main def main(): Unit =
  val fizz = LazyList.continually(List("", "", "Fizz")).flatten
  val buzz = LazyList.continually(List("", "", "", "", "Buzz")).flatten
  val numbers = LazyList.from(1).take(100)

  fizz.zip(buzz).zip(numbers).foreach { case ((f, b), n) =>
    println(stringMask(n.toString, f + b))
  }
