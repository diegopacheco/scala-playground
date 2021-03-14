package com.github.diegopacheco.scala3.playground.features

object OpenClassMain extends App{

  open class Writer[T]:
    /** Sends to stdout, can be overridden */
    def send(x: T) = println(x)
    /** Sends all arguments using `send` */
    def sendAll(xs: T*) = xs.foreach(send)
  end Writer

  import scala.language.adhocExtensions

  class StarWriter[String] extends Writer[String]:
    override def send(x:String) = {
      super.send(x)
    }
  
  StarWriter().send("hi")
  
}
