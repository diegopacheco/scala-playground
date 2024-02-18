// Option 1 to enable equality with derives CanEqual
case class Dog(name: String) derives CanEqual

// Option 2 to enable equality given and CanEqual.derived
case class Cat(name: String)
given CanEqual[Cat, Cat] = CanEqual.derived

@main def hello(): Unit =
  val gandalf = Cat("Gandalf")
  val spike   = Dog("Spike")

  // println(gandalf == spike) // compile error -  Values of types Cat and Dog cannot be compared with == or !=

  println(gandalf == gandalf)
  println(spike == spike) 