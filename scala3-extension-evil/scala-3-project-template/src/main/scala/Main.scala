@main def hello(): Unit =
  println("1".makeInt(2))     // Int = 1
  println("10".makeInt(2))    // Int = 2
  println("100".makeInt(2))   // Int = 4

extension (s: String)
  def makeInt(radix: Int): Int = Integer.parseInt(s, radix)