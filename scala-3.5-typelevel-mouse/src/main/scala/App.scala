object App extends App {

  import mouse.all._

  print(true.option("Its true!"))

  def makeEven(i: Int) = (i % 2 == 1).applyIf(i)(_ - 1)
  println(makeEven(3))
  println(makeEven(2))

  val e1: Either[String, Int] = Left("failed")
  println(e1)
  println(true.whenA(e1))
  println(false.whenA(e1))

  println(true.option("Its true!").cata(msg => s"Message received: ${msg}", "No message"))

  println("1.0".parseFloat)

  println("foo".parseIntValidated)

  val t1 = scala.util.Try(new java.net.URL("https://www.github.com"))
  println(t1.cata(msg => s"URL is valid!", error => s"URL is invalid: ${error.getMessage}"))
  println(t1.toEither)

  val intToBytes = 123456789.toByteArray
  println(intToBytes)

  val longToBase64 = 123456789L.toBase64
  println(longToBase64)

  println(5.squared)

  println(1.5 |> (_.toInt) |> (_.toString))

  println(liftEither[Option[Int]]({case Some(n) => n}, a => s"Unexpected: $a")(Some(6)))

  val mapped = Map(1 -> 2, 3 -> 4).mapKeys(_ * 2)
  println(mapped)

  val foption = List(Option(1), Option(2), Option(4)).mapIn(_ * 2)
  println(foption)

  val feither = List(Either.cond(true, 1, "0")).mapIn(_ * 2)
  println(feither)

  val listOption = List(Option(1), Option(2)).mapNested2(_ * 2)
  println(listOption)

  val res = List(0,1,2).tailOption
  println(res)

}
