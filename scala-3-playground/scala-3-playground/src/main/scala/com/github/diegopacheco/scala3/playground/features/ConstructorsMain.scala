package com.github.diegopacheco.scala3.playground.features

class Person:
  // fields
  var name: String = null
  var vocation: String = null

  // constructor
  def this(_name: String, _vocation: String) =
    this()
    name = _name
    vocation = _vocation
  
  override def toString():String = name + " " + vocation

object ConstructorsMain extends App{
  
  val p = Person(_name="Diego",_vocation="Software Architect")
  println(p)
  
}
