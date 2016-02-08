package com.github.diegopacheco.scala.playground.camel.simple

import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

class ScalaPrinter {
  def print = {
    Thread.sleep(5000)
    println("Got It!")
  }
}

object DirectApp extends App {

  val camelContext: DefaultCamelContext = new DefaultCamelContext

  camelContext.addRoutes(new RouteBuilder() {
    override def configure() {
      from("direct-vm://start").to("direct-vm://continue")
      
      from("direct-vm://continue").
        to("bean://com.github.diegopacheco.scala.playground.camel.simple.ScalaPrinter")
    }
  })

  camelContext.start
  
  while (true) {
    Thread.sleep(5000)
    camelContext.createProducerTemplate(1).sendBody("direct-vm://start","")
  }

}