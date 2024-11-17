package com.github.diegopacheco.scala.playground2x.sealedtraits

// Sealed Traits are better than Enumerations but:
//
// 1. Sealed traits do not provide an out-of-the-box solution to list all the enumeration values
// 2. There is no easy way to deserialize a case object from the enumeration name
// 3. Case objects do not have a default order based on identifiers – we need to manually include the identifier as an attribute in the sealed trait and provide an ordering function
//
// Comparing with Abstract Classes:
//  1. we can extend a class from multiple traits but from only one abstract class.
//  2. abstract classes can have constructor parameters, traits in Scala <3 don’t support this.
//
// Sealed Traits:
//  1. Inheritance Control: Sealed traits restrict where they can be extended. All subclasses must be defined in the same file as the sealed trait. This ensures that all possible subclasses are known at compile time.
//  2. Exhaustiveness Checking: The Scala compiler can perform exhaustiveness checking for pattern matching on sealed traits, ensuring that all possible cases are handled.
//
// Regular Traits:
//  1. Inheritance Control: Regular traits do not restrict where they can be extended. Subclasses can be defined in any file.
//  2. Exhaustiveness Checking: The Scala compiler cannot perform exhaustiveness checking for pattern matching on regular traits, as it does not know all possible subclasses at compile time.
//
sealed trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal

trait Spider {
  def throwWeb(): String
}

trait Man {
  def shout(): String
}

class MultipleInheritanceSpiderMan extends Man with Spider {
  override def throwWeb(): String = "SpiderMan throwWeb"
  override def shout(): String = "Shout, shout let it all out: SpiderMan, SpiderMan does whatever a spider can"
  override def toString: String = s"MultipleInheritanceSpiderMan(${throwWeb()}, ${shout()})"
}

trait Writer {
  def write(): String
}
class Author(name: String) extends Writer {
  def write(): String = s"$name I wrote books"
}

object SealedTraitsApp extends App{
  val dog = Dog("Buddy")
  val cat = Cat("Whiskers")
  println(dog)
  println(cat)

  val spiderMan = new MultipleInheritanceSpiderMan
  println(spiderMan)

  val author:Writer = new Author("Diego Pacheco")
  println(author.write())

}
