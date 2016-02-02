package com.github.diegopacheco.scala.playground.camel.simple

import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

class Source {
  def produce() = "Hi \n This \n Splits"
}

class Target {
  def consume(n: Any) = {
    Thread.sleep(1000)
    println("Got: " + n)
  }
}

object SplitApp extends App {

  val camelContext: DefaultCamelContext = new DefaultCamelContext

  camelContext.addRoutes(new RouteBuilder() {
    override def configure() {
      from("bean://com.github.diegopacheco.scala.playground.camel.simple.Source").
        split(body(classOf[String]).tokenize("\n")).
        to("bean://com.github.diegopacheco.scala.playground.camel.simple.Target")
    }
  })

  camelContext.start

  while (true) {
    Thread.sleep(10000)
  }

}