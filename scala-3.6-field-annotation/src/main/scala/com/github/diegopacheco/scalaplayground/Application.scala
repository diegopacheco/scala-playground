package com.github.diegopacheco.scalaplayground

import java.lang.reflect.Field

object Application extends App{

  private val person = new Person("John Doe")
  println(person)

  private val personClass = classOf[Person]
  private val fields: Array[Field] = personClass.getDeclaredFields
  fields.foreach { field =>
    println(s"Field: ${field.getName}, Annotations: ")
    val annotations = field.getDeclaredAnnotations
    annotations.foreach { annotation =>
      println(s"Field: ${field.getName}, Annotation: ${annotation.annotationType.getSimpleName}")
    }
  }

}