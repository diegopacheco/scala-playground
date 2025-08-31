trait PhysicalUnit
trait Meter extends PhysicalUnit
trait Second extends PhysicalUnit
case class Quantity[U <: PhysicalUnit](value: Double)

@main def main(): Unit =
  def distance: Quantity[Meter] = Quantity(100.0)
  def time: Quantity[Second] = Quantity(10.0)

  println(distance)
  println(time)
