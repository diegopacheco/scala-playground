package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

import scala.annotation.meta.field

case class Person(
  id:Long = 0L,
  firstName: Option[String] = None,
  lastName: Option[String] = None,
) {
   override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}

object Person {
   @Table(name = "person")
   case class SpringPerson(
       @(Id @field) id: Long = 0L,
       firstName: String = null,
       lastName: String = null,
   )

   def toSpring(p:Person): SpringPerson =
      SpringPerson(p.id, p.firstName.orNull, p.lastName.orNull)

   def fromSpring(p:SpringPerson): Person =
      Person(p.id, Option(p.firstName), Option(p.lastName))

}