import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*

@main def main(): Unit =
  val age: Int :| Positive = 25
  val price: Double :| Positive = 99.99

  println(s"Age: $age")
  println(s"Price: $price")

  val validAge = 30.refineUnsafe[Positive]
  println(s"Valid age: $validAge")
