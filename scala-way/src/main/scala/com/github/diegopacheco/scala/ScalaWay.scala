package com.github.diegopacheco.scala

object ScalaWay extends App{

  //
  // Java way
  //
  println("Whats your name?")
  val name = scala.io.StdIn.readLine()
  println(s"Hello $name")

  //
  // Scala way
  //
  // For Comprehension
  // Result is a Option[Unit]
  //
  for {
    _ <- Some(println("Whats your last Name?"))
    lastName <- Some(scala.io.StdIn.readLine())
    _ <- Some(println(s"Hello Mr $lastName"))
  } yield ()

}
