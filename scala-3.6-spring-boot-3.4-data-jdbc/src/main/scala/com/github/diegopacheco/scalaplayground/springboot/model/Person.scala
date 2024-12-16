package com.github.diegopacheco.scalaplayground.springboot.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import scala.annotation.meta.field

//
// 1. Scala case class is immutable by default and does not generate java setters
// 2. Never do Some(value) always do Option(value) to avoid Some(null) and null pointer exceptions
//
// Approach 1 (working) using JDBC Converters
//
@Table(name = "person")
case class Person(
  @(Id @field) id: Long = 0L,
  var firstName: Option[String] = None,
  var lastName: Option[String] = None,
) {
   override def toString: String = "Person{" + "id=" + id +
    ", " + "firstName='" + firstName + "', " + "lastName='" + lastName + "'}"
}