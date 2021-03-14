package com.github.diegopacheco.scala3.playground.features

object NamedParameterMain extends App{

  def makeConnection(url: String, timeout: Int = 5000): Unit =
    println(s"url=$url, timeout=$timeout")

  makeConnection(url="127.0.0.1",timeout = 2000)
  
}
