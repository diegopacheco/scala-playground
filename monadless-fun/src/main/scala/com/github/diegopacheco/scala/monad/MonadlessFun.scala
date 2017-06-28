package com.github.diegopacheco.scala.monad

import io.monadless.Monadless
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object MonadlessFun extends App {

  val monadless = Monadless[List]()
  import monadless._
  println("Monadless: " + monadless)
  
  val a = lift {
     unlift(List(1))
  }
  
  val b = lift {
     val x = unlift(a)
     x + 1
  }
  println(b)
  
}
