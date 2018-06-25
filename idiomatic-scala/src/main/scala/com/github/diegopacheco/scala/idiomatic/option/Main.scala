package com.github.diegopacheco.scala.idiomatic.option

object Integer{
  
  def toInt(o:Any):Option[Int] = o match {
      case i:String       => Some(i.toString().toInt)
      case Some(i:String) => Some(i.toString().toInt)
      case Some(i:Int)    => Some(i)
      case None           => Some(0)
  }
  
}

object Main extends App {
  
   println(Integer.toInt("42"))
  
}