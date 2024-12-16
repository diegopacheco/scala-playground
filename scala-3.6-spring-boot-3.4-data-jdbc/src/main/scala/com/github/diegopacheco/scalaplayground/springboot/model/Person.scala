package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import scala.annotation.meta.field
import scala.language.implicitConversions

//
// 1. Scala case class is immutable by default and does not generate java setters
// 2. Never do Some(value) always do Option(value) to avoid Some(null) and null pointer exceptions
//

@Table(name = "person")
case class Person(
  @(Id @field) id: Long = 0L,
  var firstName: Option[String] = None,
  var lastName: Option[String] = None,
) {
   def setFirstName(firstName: String): Unit = this.firstName = Option(firstName)
   def setLastName(lastName: String): Unit = this.lastName = Option(lastName)
   override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}

/*
case class Person(
  @(Id @field) id: Long = 0L,
  var firstName: Option[String] = None,
  var lastName: Option[String] = None,
) {
   override def toString: String = "Person{" + "id=" + id +
     ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}

object Person {
   @Table(name = "person")
   case class PersonMapping(
       @(Id @field) id: Long = 0L,
       firstName: String = null,
       lastName: String = null,
   )

   implicit def toDB(p: Person): Person.PersonMapping =
     PersonMapping(p.id, p.firstName.orNull, p.lastName.orNull)

   implicit def fromDB(p: Person.PersonMapping): Person =
     Person(p.id, Option(p.firstName), Option(p.lastName))
}
*/