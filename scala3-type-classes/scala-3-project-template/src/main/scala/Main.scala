/**
* Type classes in scala we can add behavior to closed classes.
* It's like Java's https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
* Haskell has type classes too :-) 
*/

// Showable type class
trait Showable[A]:
  extension (a: A) def show: String

// Show trait
trait Show:
  def show: String

case class Person(firstName: String, lastName: String)

given Showable[Person] with
  extension (p: Person) def show: String =
    s"${p.firstName} ${p.lastName}"

@main def hello(): Unit =
  val person = Person("John", "Doe")
  println(person.show)