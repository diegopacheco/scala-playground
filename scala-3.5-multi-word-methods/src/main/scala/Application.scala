object Application extends App{
  class Person(name: String) {
    def `then said that`(thing: String) = s"$name then said that: $thing"
  }

  val johnDoe = new Person("John Doe")
  println(johnDoe `then said that` "Scala 3.5 is awesome!")

}
