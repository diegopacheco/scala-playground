package com.github.diegopacheco.scala3.basic.ifs

object IFsApp extends App {

  def compare(a: Int, b: Int): Int =
    if a < b then
      -1
    else if a == b then
      0
    else
      1
    end if

  val result = compare(42,35) match {
    case -1 => "A is smaller"
    case 0 => "Equals"
    case 1 => "A is bigger"
  }

   println(s"is 42 == 35? ${result}")

}
