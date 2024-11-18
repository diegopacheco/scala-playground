package com.github.diegopacheco.scala.playground2x.ifs

object IFsApp extends App{

  val x = 10
  if (x > 0){
    println("Positive")
  } else if (x < 0){
    println("Negative")
  } else {
    println("Zero")
  }

  val odd = if (1 % 2 == 0) "1 is even" else "1 is odd"
  println(odd)

  def getAnswer(x:Int):String = if (x == 42) "The answer to the ultimate question of life, the universe and everything is 42" else "Nope"
  println(getAnswer(42))

  val even = Some(6).map(x => if (x % 2 == 0) "even" else "odd")
  println(even)

  7 match {
    case x if x % 2 == 0 => println("7 is even")
    case _ => println("7 is odd")
  }

}
