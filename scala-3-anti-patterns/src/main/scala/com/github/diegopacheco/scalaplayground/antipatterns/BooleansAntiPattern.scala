package com.github.diegopacheco.scalaplayground.antipatterns

//
// Anti-Pattern: Booleans
// 1. Booleans are not types and should not be used as types.
// 2. Booleans are not self-explanatory.
// 3. Booleans are not self-documenting.
// 4. Booleans are not self-validating.
//

def filterMe(x:List[Int],filter: (Int) => Boolean):List[Int] = {
  x.filter(filter)
}

object BooleansAntiPattern extends App{
    val x = List(1,2,3,4,5,6,7,8,9,10)

    val even = (x:Int) => x % 2 == 0
    val odd  = (x:Int) => x % 2 != 0

    println(filterMe(x,even))
    println(filterMe(x,odd))
}
