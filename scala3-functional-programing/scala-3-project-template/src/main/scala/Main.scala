@main def hello(): Unit =
  val res = List(1,2,3,4,5,6,7,8,9,10)
            .filter(_ > 3)
            .filter(_ < 7)
            .map(_ * 10)
            .product
  println(res) // 120000