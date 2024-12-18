package com.github.diegopacheco.scalap99

// P96 (**) Syntax checker.
//
// In a certain programming language (Ada)
// identifiers are defined by the syntax diagram (railroad chart) opposite.
// Transform the syntax diagram into a system of syntax diagrams which do not contain loops; i.e.
// which are purely recursive.Using these modified diagrams, write a function isIdenti
// fier that can check whether or not a given string is a legal identifier.
//
object P96 {
  def isIdentifier(str: String): Boolean = {
    def isLetter(c: Char): Boolean = c.isLetter
    def isDigit(c: Char): Boolean = c.isDigit
    def isLetterOrDigit(c: Char): Boolean = isLetter(c) || isDigit(c)

    def checkIdentifier(s: List[Char]): Boolean = s match {
      case Nil => false
      case head :: tail if isLetter(head) => checkTail(tail)
      case _ => false
    }

    def checkTail(s: List[Char]): Boolean = s match {
      case Nil => true
      case head :: tail if isLetterOrDigit(head) => checkTail(tail)
      case _ => false
    }

    checkIdentifier(str.toList)
  }
}

object P96Main extends App {
  import P96._

  val testStrings = List("validIdentifier", "123Invalid", "another_valid123", "invalid-char!")
  testStrings.foreach { str =>
    println(s"$str: ${isIdentifier(str)}")
  }
}