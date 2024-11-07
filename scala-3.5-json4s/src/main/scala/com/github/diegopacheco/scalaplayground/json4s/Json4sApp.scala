package com.github.diegopacheco.scalaplayground.json4s

import org.json4s._
import org.json4s.native.JsonMethods._

object Json4sApp extends App{
  val result = parse(""" { "numbers" : [1, 2, 3, 4] } """)
  println(result)

  val res1 = parse("""{"name":"Toy","price":35.35}""", useBigDecimalForDouble = true)
  println(res1)
}
