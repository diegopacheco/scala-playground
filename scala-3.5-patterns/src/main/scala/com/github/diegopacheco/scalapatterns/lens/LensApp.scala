package com.github.diegopacheco.scalapatterns.lens

trait Lens[S, A] {
  def get(s: S): A
  def set(s: S, a: A): S
  def modify(s: S)(f: A => A): S = set(s, f(get(s)))
}

case class Address(street: String, city: String)
case class Person(name: String, age: Int, address: Address)

val addressLens: Lens[Person, Address] = new Lens[Person, Address] {
  def get(person: Person): Address = person.address
  def set(person: Person, address: Address): Person = person.copy(address = address)
}

val streetLens: Lens[Address, String] = new Lens[Address, String] {
  def get(address: Address): String = address.street
  def set(address: Address, street: String): Address = address.copy(street = street)
}

val personStreetLens: Lens[Person, String] = new Lens[Person, String] {
  def get(person: Person): String = streetLens.get(addressLens.get(person))
  def set(person: Person, street: String): Person = addressLens.modify(person)(address => streetLens.set(address, street))
}

object LensApp extends App{
  val person = Person("John Doe", 30, Address("123 Main St", "Springfield"))

  // Get the street
  println(personStreetLens.get(person)) // Output: 123 Main St

  // Set the street
  val updatedPerson = personStreetLens.set(person, "456 Elm St")
  println(updatedPerson)
}
