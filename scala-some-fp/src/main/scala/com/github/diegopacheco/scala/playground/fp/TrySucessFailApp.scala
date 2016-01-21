package com.github.diegopacheco.scala.playground.fp

import scala.util.Try

object TrySucessFailApp extends App {
  
  def guessMyName(name:String):Try[String] = Try {   
    name match {
      case "heisenberg" => "The King"
      
      case _ => throw new IllegalArgumentException("Wrong name")
    }
  }
  
  println( "1. " + guessMyName("Diego").getOrElse(":(") ) // 1. :(
  
  try{
    println( "2. " + guessMyName("Diego").get )  // 2. Ops : Wrong name
  }catch {
    case e:Exception => println("2. Ops : " + e.getMessage);  
  }
  
  println( "3 " + guessMyName("Diego").map { x => x.toUpperCase() } ) // 3 Failure(java.lang.IllegalArgumentException: Wrong name)
  
  println( "4 " + guessMyName("heisenberg").map { x => x.toUpperCase() } ) // 4 Success(THE KING)
  
}