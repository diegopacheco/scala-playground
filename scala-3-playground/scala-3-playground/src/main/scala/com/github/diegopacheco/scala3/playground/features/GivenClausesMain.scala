package com.github.diegopacheco.scala3.playground.features

object GivenClausesMain extends App{

  case class Config(port: Int, baseUrl: String)

  def renderWebsite(path: String)(using Config): String =
    "<html>" + renderWidget(List("cart")) + "</html>"

  def renderWidget(items: List[String])(using Config): String = items.toString() 

  val config = Config(8080, "docs.scala-lang.org")
  given Config = config
    
  // option 1
  // val result = renderWebsite("/home")(using config)
  
  // option 2
  val result = renderWebsite("/home")
  
  println(result)
  
}
