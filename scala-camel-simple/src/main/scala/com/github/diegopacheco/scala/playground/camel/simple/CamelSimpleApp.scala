package com.github.diegopacheco.scala.playground.camel.simple

import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.Processor
import org.apache.camel.Exchange

class ScalaA {
  def hi() = "Hi"
}

class ScalaB {
  def whatsup(n: Any) = {
    Thread.sleep(5000)
    println("Got: " + n)
  }
}

object CamelSimpleApp extends App {

  val camelContext: DefaultCamelContext = new DefaultCamelContext

  camelContext.addRoutes(new RouteBuilder() {
    override def configure() {
      from("bean://com.github.diegopacheco.scala.playground.camel.simple.ScalaA").
        to("bean://com.github.diegopacheco.scala.playground.camel.simple.ScalaB")

      from("file://d:/diego/temp").
        to("bean://com.github.diegopacheco.scala.playground.camel.simple.ScalaB")
    }
  })

  camelContext.start

  while (true) {
    Thread.sleep(10000)
  }

}