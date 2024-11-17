package com.github.diegopacheco.scala.playground2x.collections

import scala.collection.mutable.ArrayBuffer

object CollectionsApp extends App{

  //
  // ArrayBuffer
  //
  val numbers = ArrayBuffer[Int]()
  numbers += 1
  numbers += 2
  numbers += 3
  numbers.foreach(println)

  // add multiple elements from another collection
  numbers ++= List(4, 5, 6)
  numbers.filter(_ >=4).foreach(println)

  // remove multiple elements using another collection
  numbers --= Array(1,2,3)
  numbers.foreach(println)

  numbers.append(10)
  numbers.appendAll(Seq(11,12,13))
  numbers.insertAll(0, Vector(4, 5, 6, 7))
  numbers.prepend(10)
  numbers.prependAll(Array(0, 1, 2))
  numbers.foreach(println)

  private val letters = ArrayBuffer.range('a', 'z')
  letters.insert(0,'A')
  letters.remove(0)
  letters.foreach(print)

  //
  // List
  //
  val luckyNumbers = 4 :: 8 :: 15 :: 16 :: 23 :: 42 :: Nil
  luckyNumbers.foreach( x => print(x+" "))

  //
  // Vector
  //
  val vec = Vector(1,2,3,4,5) ++ Vector(6,7)
  vec.foreach(print)

  //
  // Map
  //
  val states = Map(
    "CA" -> "California",
    "NY" -> "New York",
    "CO" -> "Colorado"
  )
  for { (k,v) <- states } println(s"key: $k, value: $v")

  val statesMut = collection.mutable.Map("AK" -> "Alaska")
  statesMut += ("AL" -> "Alabama")
  statesMut += ("AR" -> "Arkansas", "AZ" -> "Arizona")
  statesMut ++= Map("CA" -> "California", "CO" -> "Colorado")
  statesMut("AK") = "Alaska, A Really Big State"
  statesMut.foreach(println)

  //
  // Set
  //
  val set = scala.collection.mutable.Set[Int]()
  set += 1
  set += 2 += 3
  set.add(4)
  set -= 1
  set.foreach(print)


}
