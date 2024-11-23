package com.github.diegopacheco.json4sapp

import org.json4s._
import org.json4s.native.Serialization

trait Json4sSupport {
  implicit val formats: Formats = new DefaultFormats {
    override val typeHints: TypeHints = DefaultFormats.typeHints
  }
  implicit val serialization: Serialization.type = Serialization
}

trait Animal{}

case class Dog(name: String) extends Animal{}
case class Cat(name: String) extends Animal{}

class Person(val name: String) {
  override def toString: String = s"Person($name)"
}

case class ComputerModel(id: Int, name: String, manufacturerId: Option[Int]) extends Serializable{}

object Json4sApp extends App with Json4sSupport {

  val dog = Dog("Snoopy")
  val cat = Cat("Garfield")
  val person = new Person("John Doe")

  val dogJson = Serialization.write(dog)
  val catJson = Serialization.write(cat)
  val personJson = Serialization.write(person)
  println(s"Dog JSON: ${dogJson} - Cat JSON: ${catJson} - Person JSON: ${personJson}")

  val dogObj = Serialization.read[Dog](dogJson)
  val catObj = Serialization.read[Cat](catJson)
  val personObj = Serialization.read[Person](personJson)
  println(s"Dog Object: ${dogObj} - Cat Object: ${catObj} - Person Object: ${personObj}")

  val cats = List(Cat("Gandalfy"), Cat("Tom"), Cat("Felix"), Cat("Garfield"))
  val catsJson = Serialization.write(cats)
  println(s"List of Cats JSON: ${catsJson}")

  val catsVec = Vector(Cat("Gandalfy"), Cat("Tom"), Cat("Felix"), Cat("Garfield"))
  val catsVecJson = Serialization.write(catsVec)
  println(s"Vector of Cats JSON: ${catsVecJson}")

  val seqDogs = Seq(Dog("Snoopy"), Dog("Pluto"), Dog("Rex"))
  val seqDogsJson = Serialization.write(seqDogs)
  println(s"Seq of Dogs JSON: ${seqDogsJson}")

  val computer = ComputerModel(1, "MacBook Pro", Some(1))
  val computerJson = Serialization.write(computer)(formats)
  println(s"Computer JSON: ${computerJson}")

  val computerObj = Serialization.read[ComputerModel](computerJson)
  println(s"Computer Object: ${computerObj}")

}