package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import scala.annotation.meta.field
import scala.language.implicitConversions

//
// Approach 2 (working) using Mappers and Implicit Conversions
//
case class Person2(
  id: Long = 0L,
  firstName: Option[String] = None,
  lastName: Option[String] = None,
) {
   override def toString: String = "Person{" + "id=" + id +
     ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}
object Person2 {
   @Table(name = "person")
   case class PersonMapping(
       @(Id @field) id: Long = 0L,
       firstName: String = null,
       lastName: String = null,
   )

   implicit def toDB(p: Person2): Person2.PersonMapping =
     PersonMapping(p.id, p.firstName.orNull, p.lastName.orNull)

   implicit def fromDB(p: Person2.PersonMapping): Person =
     Person(p.id, Option(p.firstName), Option(p.lastName))
}