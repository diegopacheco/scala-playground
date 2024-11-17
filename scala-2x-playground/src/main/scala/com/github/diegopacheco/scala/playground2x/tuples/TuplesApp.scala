package com.github.diegopacheco.scala.playground2x.tuples

class Cat(var name: String)

object TuplesApp extends App {

  //
  //  Tuples are a convenient way to group together simple logical collections of items without using a class.
  //  Technically, Scala 2.x tuples aren’t collections classes, they’re just a convenient little container.
  //  Because they aren’t a collection, they don’t have methods like map, filter,
  //
  val bag = (3, "White", new Cat("Miau"))
  println(bag)
  println(bag._1)
  println(bag._2)
  println(bag._3)

  def getBest(): (String,Int) = {
    ("Number", 42)
  }

  val (name, number) = getBest()
  println(name)
  println(number)

  val (n, color, typeOfCat) = (2, "Gray", new Cat("Tiger"))
  println(s"n: $n, color: $color, typeOfCat: $typeOfCat")

}
