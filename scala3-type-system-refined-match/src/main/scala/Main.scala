import scala.compiletime.{constValue, error}

type IsPositive[N <: Int] = N match
  case 0 => false
  case _ => true

opaque type Positive = Int

object Positive:
  def apply[N <: Int](n: N)(using ev: IsPositive[N] =:= true): Positive = n.asInstanceOf[Positive]
  
  def fromInt(n: Int): Option[Positive] = 
    if n > 0 then Some(n.asInstanceOf[Positive]) else None

extension (p: Positive)
  def value: Int = p.asInstanceOf[Int]

def sqrt(n: Positive): Double = math.sqrt(n.value)

@main def main(): Unit =
  val pos10 = Positive.fromInt(10).get
  println(s"sqrt(10) = ${sqrt(pos10)}")
  
  val input = -10
  Positive.fromInt(input) match
    case Some(pos) => println(s"sqrt($input) = ${sqrt(pos)}")
    case None => println(s"Error: $input is not positive")
  
  val input2 = 25
  Positive.fromInt(input2) match
    case Some(pos) => println(s"sqrt($input2) = ${sqrt(pos)}")
    case None => println(s"Error: $input2 is not positive")
