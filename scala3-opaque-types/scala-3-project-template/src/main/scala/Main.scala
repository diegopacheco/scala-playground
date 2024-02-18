/**
 * Opaque type == type abstraction without overhead
 */
object Logarithms:
  opaque type Logarithm = Double

  object Logarithm:
    def apply(d: Double): Logarithm = math.log(d)

  extension (x: Logarithm)
    def toDouble: Double = math.exp(x)
    def + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
    def * (y: Logarithm): Logarithm = x + y

@main def hello(): Unit =
  val n:Logarithms.Logarithm = Logarithms.Logarithm(10.5)
  println(n.toDouble)  
  println(n + n)
