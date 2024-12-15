package com.github.diegopacheco.scalajava.conversions

import scala.jdk.CollectionConverters.*

object ConversionsApp extends App{

  val javaIterator: java.util.Iterator[Int] = java.util.Arrays.asList(1, 2, 3).iterator()
  val scalaIterator: Iterator[Int] = javaIterator.asScala
  println(s"Scala Iterator: ${scalaIterator.toList}")
  println(s"Java Iterator: ${scalaIterator.asJava}")

  val javaList: java.util.List[Int] = java.util.Arrays.asList(1, 2, 3)
  val scalaList: List[Int] = javaList.asScala.toList
  println(s"Scala List: ${scalaList}")
  println(s"Java List: ${scalaList.asJava}")

  val javaList2: java.util.List[Int] = java.util.Arrays.asList(1, 2, 3)
  val scalaList2: List[Int] = javaList2.asScala.toList
  println(s"Scala List: ${scalaList2}")
  println(s"Java List: ${scalaList2.asJava}")

}
