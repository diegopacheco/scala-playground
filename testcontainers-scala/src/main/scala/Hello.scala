object Hello extends App {
  val p = new Person("Diego")
  println(s"Hello ${p.name}")
}

class Person(var name: String)