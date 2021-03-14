package com.github.diegopacheco.scala3.playground.features

class Person2:
  // fields
  var name: String = null
  var vocation: String = null

  // constructor
  def this(_name:String="",_vocation:String) =
    this()
    name = _name
    vocation = _vocation
  
  override def toString():String = name + " " + vocation

object ConstructorsMain extends App{
  
  val p = Person2(_name="Diego","Software Architect")
  println(p)
  
}
