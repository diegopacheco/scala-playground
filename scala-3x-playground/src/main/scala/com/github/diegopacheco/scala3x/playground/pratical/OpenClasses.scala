package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/other-new-features/open-classes.html
//
// Open Classes
//
// An open modifier on a class signals that the class is planned for extensions.
// Close by default unless on the same file or with a flag: import scala.language.adhocExtensions or -language:adhocExtensions
//
object OpenClasses extends App:

  open class Animal:
    def sound: String = "Some Animal Sound"

  class Dog extends Animal:
    override def sound: String = "Bark"

  val dog = Dog()
  println(dog.sound)