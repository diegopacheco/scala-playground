package com.github.diegopacheco.scala3x.playground

object Enums extends App:
  enum Color(val rgb: Int):
    case Red extends Color(0xFF0000)
    case Green extends Color(0x00FF00)
    case Blue extends Color(0x0000FF)

  val red = Color.Red
  println(red.rgb)