package com.github.diegopacheco.scalapatterns.composingoptics

case class Lens[S, A](get: S => A, set: (S, A) => S)
object Lens {
  def compose[S, A, B](outer: Lens[S, A], inner: Lens[A, B]): Lens[S, B] = Lens(
    get = s => inner.get(outer.get(s)),
    set = (s, b) => outer.set(s, inner.set(outer.get(s), b))
  )
}

case class Prism[S, A](getOption: S => Option[A], reverseGet: A => S)
object Prism {
  def compose[S, A, B](outer: Prism[S, A], inner: Prism[A, B]): Prism[S, B] = Prism(
    getOption = s => outer.getOption(s).flatMap(inner.getOption),
    reverseGet = b => outer.reverseGet(inner.reverseGet(b))
  )
}

object ComposingOpticsApp extends App{
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  val addressLens: Lens[Person, Address] = Lens(
    get = _.address,
    set = (person, address) => person.copy(address = address)
  )

  val streetLens: Lens[Address, String] = Lens(
    get = _.street,
    set = (address, street) => address.copy(street = street)
  )

  val personStreetLens: Lens[Person, String] = Lens.compose(addressLens, streetLens)
  val person = Person("John Doe", Address("123 Main St", "Anytown"))
  val updatedPerson = personStreetLens.set(person, "456 Elm St")
  println(updatedPerson) // Person(John Doe, Address(456 Elm St, Anytown))
}
