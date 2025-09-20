type Inc[N] = N match
  case 0 => 1
  case 1 => 2
  case 2 => 3
  case 3 => 4
  case 4 => 5
  case 5 => 6
  case 6 => 7
  case 7 => 8
  case 8 => 9
  case 9 => 10
  case 10 => 11

type Plus1[T] = T match
  case EmptyTuple => EmptyTuple
  case h *: t => Inc[h] *: Plus1[t]

object PlusOne {
    def runPlusOne(): Unit = {
        type Original = (0, 1, 2, 5, 9)
        type Incremented = Plus1[(0, 1, 2, 5, 9)] // (1, 2, 3, 6, 10)
        
        // Works
        val original: Original = (0, 1, 2, 5, 9)
        val incremented: Incremented = (1, 2, 3, 6, 10)
        
        println(s"Original: $original")
        println(s"Plus1: $incremented")
        
        // Would not compile - type error
        //val invalid: Incremented = (1, 2, 3, 6, 11)
        //println(s"Invalid: $invalid")
    }
}