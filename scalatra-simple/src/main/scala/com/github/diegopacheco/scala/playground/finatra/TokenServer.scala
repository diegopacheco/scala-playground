package com.github.diegopacheco.scala.playground.finatra

import org.scalatra._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler }
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context mount (new MyServlet, "/*")
  }
}

class MyServlet extends ScalatraServlet {
  get("/") {
  "[ " + 
    " {\"token\":\"3051939411\"," + 
    "\"hostname\":\"ubuntu-vm\" , " +  
    "\"dc\":\"localdc\"," +
    "\"ip\":\"10.99.4.17\"," + 
    "\"zone\":\"Default\","+
    "\"location\":\"Default\"} " +
  "]"
  }
}

object JettyLauncher extends App {
  val port = 8080

  val server = new Server(port)
  val context = new WebAppContext()
  context setContextPath "/"
  context.setResourceBase("src/main/webapp")
  context.addEventListener(new ScalatraListener)
  context.addServlet(classOf[DefaultServlet], "/")
  
  context setContextPath "/"
  context.setResourceBase("src/main/webapp")
  context.setInitParameter(ScalatraListener.LifeCycleKey, "com.github.diegopacheco.scala.playground.finatra.ScalatraBootstrap")
  context.addEventListener(new ScalatraListener)

  server.setHandler(context)
  server.start
  server.join
}