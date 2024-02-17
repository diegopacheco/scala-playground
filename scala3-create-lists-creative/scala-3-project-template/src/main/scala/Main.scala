@main def hello(): Unit =
  val a = List(1, 2, 3)           // a: List[Int] = List(1, 2, 3)
  val b = (1 to 5).toList         // b: List[Int] = List(1, 2, 3, 4, 5)
  val c = (1 to 10 by 2).toList   // c: List[Int] = List(1, 3, 5, 7, 9)
  val e = (1 until 5).toList      // e: List[Int] = List(1, 2, 3, 4)
  val f = List.range(1, 5)        // f: List[Int] = List(1, 2, 3, 4)
  val g = List.range(1, 10, 3)    // g: List[Int] = List(1, 4, 7)

  val vehiclesList:List[String] = "Truck" :: "Car" :: "Bike" :: Nil
  println(vehiclesList)

  val filledList:List[String] = List.fill(3)(".")
  println(filledList)

  val tabulatedList:List[String] = List.tabulate(3) (n => n.toBinaryString)
  println(tabulatedList)  // prints List("0", "1", "10")

  val lista = List(10, 20, 30, 40, 10)      // List(10, 20, 30, 40, 10)
  println(lista.drop(2))                    // List(30, 40, 10)
  println(lista.dropWhile(_ < 25))          // List(30, 40, 10)
  println(lista.filter(_ < 25))             // List(10, 20, 10)
  println(lista.slice(2,4))                 // List(30, 40)
  println(lista.tail)                       // List(20, 30, 40, 10)
  println(lista.take(3))                    // List(10, 20, 30)
  println(lista.takeWhile(_ < 30))          // List(10, 20)