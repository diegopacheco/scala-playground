/**
 * Methods vs Functions
 * 
 * Scala 3 allow to have methods outside of classes.
 * 
 * Unlike methods, functions are complete objects themselves, 
 * making them first-class entities.
 * 
 *
 */
@main def hello(): Unit =
  def isEvenMethod(i: Int) = i % 2 == 0         // a method
  val isEvenFunction = (i: Int) => i % 2 == 0   // a function

  println(isEvenMethod(2))
  println(isEvenFunction(2))

  val functions = List(isEvenFunction)   // works
  val methods = List(isEvenMethod)       // works

  println(functions)
  println(methods)
