package com.github.diegopacheco.scalaplayground.json4s

import org.json4s._
import org.json4s.native.JsonMethods._

object Json4sApp extends App{
  val result = parse(""" { "numbers" : [1, 2, 3, 4] } """)
  println(result)

  val res1 = parse("""{"name":"Toy","price":35.35}""", useBigDecimalForDouble = true)
  println(res1)

  import org.json4s.JsonDSL._
  import org.json4s.JsonDSL.WithDouble._

  var json = List(1, 2, 3)
  println(compact(render(json)))

  val json1 = ("name" -> "joe")
  println(compact(render(json1)))

  val json2 = ("name" -> "joe") ~ ("age" -> 35)
  println(compact(render(json2)))

  val json3 = ("name" -> "joe") ~ ("age" -> Some(35))
  println(compact(render(json3)))

}
