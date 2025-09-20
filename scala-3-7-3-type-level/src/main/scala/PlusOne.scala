import scala.compiletime.ops.int.*

type Inc[N <: Int] = N + 1

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