package com.github.diegopacheco.scalaplayground

object EnumApp extends App{
  val monday = Weekday.Mon
  println(s" monday == ${monday}")
  println(s" monday.nextDay.nextDay == ${monday.nextDay.nextDay}")
  println(s" monday.prevDay == ${monday.prevDay}")
}
