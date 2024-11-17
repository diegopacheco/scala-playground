package com.github.diegopacheco.scala.playground2x

import org.scalatest.funsuite.AnyFunSuite

class Person(var name: String)

class HelloTests extends AnyFunSuite {

  test("the name is set correctly in constructor") {
    val p = new Person("Barney Rubble")
    assert(p.name == "Barney Rubble")
  }

  test("a Person's name can be changed") {
    val p = new Person("Chad Johnson")
    p.name = "Ochocinco"
    assert(p.name == "Ochocinco")
  }

}