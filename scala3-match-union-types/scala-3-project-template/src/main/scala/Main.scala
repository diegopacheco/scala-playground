@main def hello(): Unit =
  tellMe(1)
  tellMe("1")

  val b = 0
  val works: String | Int = if (1>b) "yes" else -1
  println(works)

  // literal type in scala 3 - 3.14 <: Double
  val pi: 3.14 = 3.14 
  println(pi)

  theAnwserOfLifeUniverIs(Some(42))

  // E007 - Type Mimatch
  //theAnwserOfLifeUniverIs(Some(43))
  // [error]    |            ^^
  // [error]    |             Found:    (43 : Int)
  // [error]    |             Required: (42 : Int)


def tellMe(a: String | Int) = a match {
  case _: String => println(s"We got an String ${a}")
  case _: Int    => println(s"We got an Int ${a}")
}

def theAnwserOfLifeUniverIs(a:Option[42]) = a.foreach(println)