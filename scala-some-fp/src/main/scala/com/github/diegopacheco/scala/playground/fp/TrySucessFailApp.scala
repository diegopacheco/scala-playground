package com.github.diegopacheco.scala.playground.fp

import scala.util.Try

object TrySucessFailApp extends App {
  
  def guessMyName(name:String):Try[String] = Try {   
    name match {
      case "heisenberg" => "The King"
      
      case _ => throw new IllegalArgumentException("Wrong name")
    }
  }
  
  println( "1. " + guessMyName("Diego").getOrElse(":(") )
  
  try{
    println( "2. " + guessMyName("Diego").get )  
  }catch {
    case e:Exception => println("2. Ops : " + e.getMessage);  
  }
  
  println( "3 " + guessMyName("Diego").map { x => x.toUpperCase() } )
  
  println( "4 " + guessMyName("heisenberg").map { x => x.toUpperCase() } )
  
}