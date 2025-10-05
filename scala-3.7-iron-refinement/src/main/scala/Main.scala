import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.constraint.string.*
import io.github.iltotore.iron.constraint.collection.*

object Main extends App {

  type PositiveInt = Int :| Positive
  type Age = Int :| Interval.Closed[0, 120]
  type NonEmptyString = String :| MinLength[1]

  val validAge: Age = 25.refine
  println(s"Valid age: $validAge")

  val name: NonEmptyString = "Diego".refine
  println(s"Name: $name")

  val positiveNum: PositiveInt = 42.refine
  println(s"Positive number: $positiveNum")

  val temperature: Int :| Interval.Open[-50, 50] = 20.refine
  println(s"Temperature: $temperature")

  println("All refinements validated successfully!")
}
