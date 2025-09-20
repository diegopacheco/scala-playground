import scala.compiletime.ops.int.*

type If[C <: Boolean, A, B] <: Any = C match
  case true  => A
  case false => B

type Plus18[N <: Int] = If[N < 18, Nothing, N]

object Plus18 {
    def runPlus18(): Unit = {
        type ValidAge   = Plus18[25]  // reduces to the singleton type 25
        type InvalidAge = Plus18[16]

        // Works
        val adult: ValidAge = 25
        println(adult)

        // Works
        val ok:Plus18[30] = 30
        println(ok)

        // compile errors (uncomment to see them):
        //val minor: InvalidAge = 16
        //println(minor)
    }
}
  