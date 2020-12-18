package com.github.diegopacheco.scala.playground.chronoscala

object ChronoApp extends App{

  import jp.ne.opt.chronoscala.Imports._

  val futurePlus2Months = ZonedDateTime.now() + 2.months
  println(futurePlus2Months)

  val sum = 2.hours + 45.minutes + 10.seconds
  println(sum)

}
