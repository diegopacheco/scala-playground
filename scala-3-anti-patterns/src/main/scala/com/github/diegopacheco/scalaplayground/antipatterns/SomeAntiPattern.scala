package com.github.diegopacheco.scalaplayground.antipatterns

def getLastPerson:Option[String] = {
  // imagine people come from DB
  val people = List("Diego","Lucas","Joaquim")
  if ((Math.random()*10)<=3) Some(people.last) else Some(null)
}

/*
 * Eventually you will get:
   Some(null)
   null
 */
object SomeAntiPattern extends App{
  val lastPerson = getLastPerson
  println(lastPerson)
  println(lastPerson.get)
}
