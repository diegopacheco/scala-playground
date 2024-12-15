package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import scala.annotation.meta.field

case class Person(
  @(Id @field) id:Long = 0L,
  firstName: String = null,
  lastName: String = null,
) {
   override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}