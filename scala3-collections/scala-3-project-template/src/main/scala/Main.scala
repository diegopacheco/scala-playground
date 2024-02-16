@main def hello(): Unit =
  listMeUp()
  mapMeUp()
  setMeUp()

def listMeUp():Unit =
    val resList = (1 to 10 by 2).toList 
    println(resList)

    val a = List(10, 20, 30, 40, 10) 
    println(a.drop(2))                             // List(30, 40, 10)
    println(a.dropWhile(_ < 25))                   // List(30, 40, 10)
    println(a.filter(_ < 25))                      // List(10, 20, 10)
    println(a.slice(2,4))                          // List(30, 40)
    println(a.tail)                                // List(20, 30, 40, 10)
    println(a.take(3))                             // List(10, 20, 30)
    println(a.takeWhile(_ < 30))                   // List(10, 20)

def mapMeUp():Unit =
  val states = Map(
   "RS" -> "Porto Alegre",
   "SC" -> "Santa Catarina",
   "SP" -> "Sao Paulo",
   "RJ" -> "Rio de Janeiro",
  )
  for (k, v) <- states do println(s"key: $k, value: $v")

  println(states("RS"))

  val res = states.get("AM") match {
      case Some("Porto Alegre")   => "Bah"
      case Some("Santa Catarina") => "Segue sempre toda vida"
      case Some("Sao Paulo")      => "Entao mano"
      case Some("Rio de Janeiro") => "Caraca meu irmao"
      case _                      => "Eita"
  }
  println(res)

  val bigger = states + ("AM" -> "Manaus") // add to map
  println(bigger)

  val smaller = states - "RJ"              // remove from map
  println(smaller)

def setMeUp():Unit =
  val nums = Set(1, 2, 2, 3, 3)           // Set(1, 2, 3)
  val letters = Set('a', 'b', 'c', 'a')   // Set('a', 'b', 'c')

  println(nums)
  println(letters)

  val bigger = Seq(4,5,6) ++ nums         // merge List(4, 5, 6, 1, 2, 3) 
  println(bigger)

  println(('A' to 'Z').toList)  // List(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z)

