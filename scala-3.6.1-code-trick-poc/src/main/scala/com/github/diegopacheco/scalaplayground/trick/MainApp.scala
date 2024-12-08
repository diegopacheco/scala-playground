package com.github.diegopacheco.scalaplayground.trick

object MainApp extends App{

  val p = Person("John doe", 40)
  val dt = DateService().getDate

  println(s"Person: ${p.name} - ${p.age} - date is ${dt}")
}
