package com.github.diegopacheco.scala3x.playground.pratical

object NewCollectionFunctions extends App:

  val words = List("apple", "banana", "cherry", "date")
  val wordLengths = words.groupMapReduce(_.head)(_.length)(_ + _)
  println(wordLengths)

  val numbers = List(1, 2, 3, 4, 5)
  numbers.tapEach(println).map(_ * 2)