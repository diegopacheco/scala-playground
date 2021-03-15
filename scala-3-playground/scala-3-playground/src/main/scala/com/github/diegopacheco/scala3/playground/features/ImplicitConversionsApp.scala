package com.github.diegopacheco.scala3.playground.features

@main def ImplicitConversionsApp():Unit = {
  
  given Conversion[String, Int] with
    def apply(s: String): Int = Integer.parseInt(s)

  // a method that expects an Int
  def plus1(i: Int) = i + 1

  // pass it a String that converts to an Int
  println(plus1("1"))
  
}
