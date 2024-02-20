@main def hello(): Unit =
  def byValue(i:Int)         = i  // value      - same cache
  def byName(i: => Int): Int = i  // literally  - reevaluation

  println(byValue(2+6))   // 8 is passed
  println(byName(2+6))    // 2+6 is passed