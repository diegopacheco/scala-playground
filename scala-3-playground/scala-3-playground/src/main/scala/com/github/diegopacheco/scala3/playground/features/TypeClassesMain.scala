package com.github.diegopacheco.scala3.playground.features

object TypeClassesMain extends App{

  trait Show:
    def show:String

  trait Showable[A]:
    extension(a:A) def show:String

  case class Person(firstName: String, lastName:String)

  given Showable[Person] with
    extension(p:Person) def show:String =
      s"${p.firstName} ${p.lastName}"

  val person = Person("John", "Doe")
  println(person.show)

  def showAll[S:Showable](xs:List[S]): Unit =
    xs.foreach(x => println(x.show))

  showAll(List(Person("Jane","Doe"), Person("Mary","Doe")))
  
}
