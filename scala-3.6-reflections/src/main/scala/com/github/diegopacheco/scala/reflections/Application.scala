package com.github.diegopacheco.scala.reflections

import com.github.diegopacheco.scala.reflections.service.ReflectionService

object Application extends App {

  val person = new Person("John Doe",50, "124 main st")
  println(person)

  println("List of methods: " + ReflectionService.listMethods(classOf[Person]))

  ReflectionService.setValue(classOf[Person],person,"name","John Smith")
  println(person)
}