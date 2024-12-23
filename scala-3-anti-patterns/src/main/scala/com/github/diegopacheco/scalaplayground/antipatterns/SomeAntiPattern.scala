package com.github.diegopacheco.scalaplayground.antipatterns

//
// Some/Option Anti-Patterns
// 1. Using Some(null) - This is a common mistake, Some(null) is not the same as None.
// 2. Using get() - This is a common mistake, get() is not safe and can throw a exception.
//
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
