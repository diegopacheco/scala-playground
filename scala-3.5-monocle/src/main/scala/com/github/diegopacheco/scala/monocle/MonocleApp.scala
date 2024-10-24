package com.github.diegopacheco.scala.monocle

case class Address(streetNumber: Int, streetName: String)
case class User(name: String, address: Address)

object MonocleApp extends App{

  import monocle.syntax.all._

  val user = User("Anna", Address(12, "high street"))
  println(user.focus(_.name).replace("Bob"))
  println(user.focus(_.address.streetName).modify(_.toUpperCase))
  println(user.focus(_.address.streetNumber).get)

}
