package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import scala.annotation.meta.field

case class Person(
  @(Id @field) var id:Long = 0L,
  var firstName: Option[String] = None,
  var lastName: Option[String] = None
) {
  def this(firstName: Option[String], lastName:Option[String]) =
    this(0L, firstName, lastName)

  override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}

object Person {
  def apply(): Person = new Person(0L, None, None)
}