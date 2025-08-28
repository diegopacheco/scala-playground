import scala.compiletime.ops.int.*

type Fibonacci[N <: Int] <: Int = N match
  case 0 => 0
  case 1 => 1
  case _ => Fibonacci[N - 1] + Fibonacci[N - 2]

@main def main():Unit =
  val fibo0: Fibonacci[0] = valueOf[Fibonacci[0]]
  val fibo1: Fibonacci[1] = valueOf[Fibonacci[1]]
  val fibo3: Fibonacci[3] = valueOf[Fibonacci[3]]
  val fibo5: Fibonacci[5] = valueOf[Fibonacci[5]]
  val fibo6: Fibonacci[6] = valueOf[Fibonacci[6]]
  val fibo10: Fibonacci[10] = valueOf[Fibonacci[10]]
  
  println(s"F(0) = $fibo0")
  println(s"F(1) = $fibo1") 
  println(s"F(3) = $fibo3")
  println(s"F(5) = $fibo5")
  println(s"F(6) = $fibo6")
  println(s"F(10) = $fibo10")
