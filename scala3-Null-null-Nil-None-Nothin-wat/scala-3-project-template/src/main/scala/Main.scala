@main def hello(): Unit =
  val thisIsnull = null
  println(thisIsnull)

  val refNull:Null = null       // Null Reference(Ref)
  println(refNull)

  val noValue = None            // opossite of Some(2)
  println(noValue)

  val lista:List[Int] = Nil     // Empty
  println(lista)      
  println(lista.length)         // 0

  val theCombatUnit: Unit = ()  // Unit - like void in Java
  println(theCombatUnit)
  
  val nothigHere: String = throw new RuntimeException("Oopsy") // Nothing
  println(nothigHere)
