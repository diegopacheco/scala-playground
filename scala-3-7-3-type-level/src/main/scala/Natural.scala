sealed trait Nat
case object Zero extends Nat
case class Succ[N <: Nat](n: N) extends Nat

type One = Succ[Zero.type]
type Two = Succ[One]
type Three = Succ[Two]

type Add[A <: Nat, B <: Nat] <: Nat = A match
  case Zero.type => B
  case Succ[n] => Succ[Add[n, B]]

object Natural {
  def runNatural(): Unit = {
    val result: Add[Two, One] = Succ(Succ(Succ(Zero)))
    println(result)
  }
}