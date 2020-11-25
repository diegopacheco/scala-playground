
object MainAp extends App{

  case class Person(val name:String,val age:Int)

  val people: List[Person] = List(Person("Diego",36),Person("Andressa",36),Person("Melina",6),Person("Gandalf",6))

  // Partition `people` into two arrays `minors` and `adults`.
  // Use the anonymous function `(_.age < 18)` as a predicate for partitioning.
  val (minors, adults) = people partition (_.age < 18)
  println(s"Adults: $adults Minors: $minors")

}