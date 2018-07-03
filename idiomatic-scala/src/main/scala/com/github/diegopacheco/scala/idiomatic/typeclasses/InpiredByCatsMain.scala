package com.github.diegopacheco.scala.idiomatic.typeclasses

object InpiredByCats extends App {
  
  // Type Class = Type system functionality
  trait Show[T]{
    def show(value:T):String
  }

  // Type Instance = All Instances for Supported Types
  object ShowInstances{
    implicit val intInstance = new Show[Int] {
      override def show(value:Int):String = value.toString()
    }
    implicit val StringInstance = new Show[String] {
      override def show(value:String):String = s"-- ${value} --"
    }
  }
  
  // Type Interface - Functions for the Type Class instances
  object ShowInterface{
    def printWithShow[T](value:T)(implicit show:Show[T]):Unit = println( show.show(value) ) 
  }
  
  // Beatiful Syntax Support
  implicit class ShowSyntax[T](value:T){
    def printWithShow(implicit show:Show[T]):Unit = println(show.show(value))
  }
  
  import ShowInstances._

  ShowInterface.printWithShow("There we Go")
  
  "Diego".printWithShow
  42.printWithShow
  
}