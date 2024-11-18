package com.github.diegopacheco.scala.playground2x.loops

object LoopsApp extends App{

  for (i <- 1 to 5){
    println(s"i: $i")
  }

  var total = 3
  while(total > 0){
    println(s"total: $total")
    total -= 1
  }

  for {
    n <- 1 to 3
    m <- 1 to 3
    result = n * m
    _ = if (n == 2 && m == 2) {
      println("Printing once. n=2 and m=2")
    }
  } yield ()

  val res = for {
    i <- 1 to 10
    x = if (i % 2 == 0) Some(i * 2) else None
  } yield x

  val filteredRes = res.flatten
  println(filteredRes)

  // tail recursion factorial sample with annotation
  @scala.annotation.tailrec
  def factorial(n: Int, acc: Int = 1): Int = {
    if (n <= 0) acc
    else factorial(n - 1, n * acc)
  }
  println(factorial(5))

  val map = Map("R" -> "Red", "G" -> "Green", "B" -> "Blue")
  for ((key,value) <- map) {
    println(s"""$key is for $value""")
  }

  val list = List(1, 2, 3, 4, 5)
  list.foreach(println)

}
