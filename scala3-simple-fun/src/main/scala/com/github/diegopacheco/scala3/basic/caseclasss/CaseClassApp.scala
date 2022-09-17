package com.github.diegopacheco.scala3.basic.caseclasss

object CaseClassApp extends App {

  case class Person(name: String)

  def speak(p: Person) = p match
    case Person(name) if name == "Fred" => println(s"$name says, Yubba dubba doo")
    case Person(name) if name == "Bam Bam" => println(s"$name says, Bam bam!")
    case _ => println("Watch the Flintstones!")

  speak(Person("Fred")) // "Fred says, Yubba dubba doo"
  speak(Person("Bam Bam")) // "Bam Bam says, Bam bam!"

}
