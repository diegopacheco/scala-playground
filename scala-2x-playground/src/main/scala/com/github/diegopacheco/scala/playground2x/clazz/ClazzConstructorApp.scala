package com.github.diegopacheco.scala.playground2x.clazz

class Android(var serialNumber: String, var alias: String) {

  println("Android Core - the constructor begins")
  var flag = true

  private val HOME = System.getProperty("user.home")
  override def toString(): String = s"toString() == serialNumber = $serialNumber, alias = $alias"

  def printHome(): Unit = println(s"HOME = $HOME")
  def print(): Unit = println(this)

  printHome()
  print()
  println("Android Core - you've reached the end of the constructor")
}

object ClazzConstructorApp extends App{
  val c = new Android("C3P0", "DB-PoolRobot")
  println(c)
}
