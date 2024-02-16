@main def hello(): Unit =
  mapItUp()

def mapItUp():Unit =
  val a = List(1, 2, 3).map(i => i * 2)        // List(2,4,6)
  println(a)

  val b = List(3, 4, 5).map(_ * 2)             // List(6,8,10)
  println(b)

  def double(i: Int): Int = i * 2
  println(List(1, 2, 3).map(i => double(i)))   // List(2,4,6)
  println(List(1, 2, 3).map(double))           // List(2,4,6)  
