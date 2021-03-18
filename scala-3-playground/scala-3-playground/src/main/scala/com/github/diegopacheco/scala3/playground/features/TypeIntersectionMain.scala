package com.github.diegopacheco.scala3.playground.features

object TypeIntersectionMain extends App{

  trait Resettable:
    def reset(): Unit

  trait Growable[A]{
    def add(a:A): Unit  
  }

  trait Both[A] extends Resettable, Growable[A]
  
  def callMe(x:Both[String]): Unit =
    x.reset()
    x.add("first")

  class DoItAll extends Both[String]:
    override def reset()=println("Reseting... ")
    override def add(a:String)=println(s"adding ${a}")
  
   
  val x:Resettable & Growable[String] = DoItAll()
  
  val c:Both[String] = DoItAll()
  callMe(c)
  
}
