package com.github.diegopacheco.scala.playground.fp

import java.util.Date

object ForCompreensions extends App {
  
  case class Person(initial:Char,salary:Int,bestFriend:String)

  val p = for {
    n <- "ABCDE"
    s <- List(10,20,30,40,50)
    l <- List("John","Paul", "Pablo","Don","Eladio")
  } yield Person(n,s,l) 
  
  println(p)
  
}