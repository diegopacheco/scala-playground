package com.github.diegopacheco.scala.playground2x.option_monad

object Safety{
  def div(x:Int,y:Int):Option[Int] = {
    if (y==0) None else Some(x/y)
  }
}

object OptionMonadApp extends App{

  val result = Safety.div(10,2)
  println(result)
  println(Safety.div(10,0))

  Safety.div(100,50) match {
    case Some(value) => println(s"Result: ${value}")
    case None => println("Error")
  }

}
