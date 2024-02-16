case class Person(name: String, var profession: String)

@main def hello(): Unit =
  val john  = Person("John","Engineer")
  val marry = Person("Marry","Analyst")

  println(john.name)
  println(john)
  println(john.equals(Person("John","Engineer")))
  println(john == Person("John","Engineer"))

  // john.name = "Petter" //  Reassignment to val name - dont compile
  john.profession = "Prompt Engineer"
  println(john)