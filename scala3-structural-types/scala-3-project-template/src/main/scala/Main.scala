class Record(elems: (String, Any)*) extends Selectable:
  private val fields = elems.toMap
  def selectDynamic(name: String): Any = fields(name)

type Person = Record {
  val name: String
  val age: Int
}

@main def hello(): Unit =
  val person = Record(
    "name" -> "John Doe",
    "age" -> 62
  ).asInstanceOf[Person]

  println(s"${person.name} is ${person.age} years old.")