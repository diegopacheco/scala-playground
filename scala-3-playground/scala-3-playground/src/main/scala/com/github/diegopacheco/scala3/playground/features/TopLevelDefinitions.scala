package com.github.diegopacheco.scala3.playground.features

import scala.collection.mutable.ArrayBuffer

enum Topping:
  case Cheese, Pepperoni, Mushrooms

import Topping._
class Pizza:
  val toppings = ArrayBuffer[Topping]()
  override def toString = toppings.toString()

val p = Pizza()

extension (s: String)
  def capitalizeAllWords = s.split(" ").map(_.capitalize).mkString(" ")

val hwUpper = "hello, world".capitalizeAllWords

type Money = BigDecimal

@main def myApp =
  p.toppings += Cheese
  println("show me the code".capitalizeAllWords)
  println(p)