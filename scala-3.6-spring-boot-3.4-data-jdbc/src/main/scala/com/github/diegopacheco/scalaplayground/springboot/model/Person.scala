package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import scala.annotation.meta.field

case class Person(
  @(Id @field) var id:Long = 0L,
  var firstName: String = null,
  var lastName: String = null,
) {
  override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}
