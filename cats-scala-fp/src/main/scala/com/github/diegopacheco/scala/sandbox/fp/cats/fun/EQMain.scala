package com.github.diegopacheco.scala.sandbox.fp.cats.fun

object EQMain extends App {
  
  import java.util.Date
  import cats.Eq
  import cats.syntax.eq._
  import cats.instances.long._
  
  implicit val dateEq:Eq[Date] = 
    Eq.instance[Date] { (date1,date2) =>
      date1.getTime === date2.getTime
    }
  
   val x = new Date()
   Thread.sleep(1000)
   val y = new Date()
   
   println( x === x )
   println( y === x )
  
}