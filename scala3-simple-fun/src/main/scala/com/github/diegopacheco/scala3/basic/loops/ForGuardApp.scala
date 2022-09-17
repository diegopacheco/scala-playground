package com.github.diegopacheco.scala3.basic.loops

object ForGuardApp extends App{
  for
    i <- 1 to 10
    if i % 2 == 0
  do
    println(i)
}
