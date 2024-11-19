package com.github.diegopacheco.scala.playground2x.obj

class Person {
  var name: Option[String] = None
  var age: Option[Int] = None
  override def toString = s"$name, $age"
}

object Person {
  // a one-arg constructor
  def apply(name: Option[String]): Person = {
    var p = new Person
    p.name = name
    p
  }
  // a two-arg constructor
  def apply(name: Option[String], age: Option[Int]): Person = {
    var p = new Person
    p.name = name
    p.age = age
    p
  }
}

object CompanionObjectApp extends App{
  val p1 = Person(Some("John Doe"), Some(50))
  val p2 = Person(Some("Marry Doe"))

  println(p1)
  println(p2)
  println(p1 == p2)
  println(p1 eq p2)
}
