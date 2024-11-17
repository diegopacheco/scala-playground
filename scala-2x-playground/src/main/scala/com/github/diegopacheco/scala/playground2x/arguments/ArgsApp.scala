package com.github.diegopacheco.scala.playground2x.arguments

object DefaultArgs{
  def sum(a:Int=1,b:Int=2):Int = a + b
}

object NamedArgs{
  def sum(a:Int,b:Int):Int = a + b
}

//
// Call-by-Value
//   The argument is evaluated before the function is called.
//   The evaluated value is passed to the function.
//   The argument is evaluated only once.
//
// Call-by-Name
//   The argument is not evaluated before the function is called.
//   The expression is passed to the function and is evaluated every time it is used within the function.
//   The argument can be evaluated multiple times.
//
object CallByValue {
  def printValue(x: Int): Unit = {
    println("Value: " + x)
    println("Value: " + x)
  }
}

object CallByName {
  def printValue(x: => Int): Unit = {
    println("Value: " + x)
    println("Value: " + x)
  }
}

object ArgsApp extends App{

  val res = DefaultArgs.sum()
  println(res)

  val res1 = DefaultArgs.sum(a = 10,b = 20)
  println(res1)

  def getValue(): Int = {
    println("Evaluating getValue")
    42
  }

  println("----")
  println(s"Call-by-Value: ${CallByValue.printValue(getValue())}")
  println("----")
  println(s"Call-by-Name: ${CallByName.printValue(getValue())}")

}
