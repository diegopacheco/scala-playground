package com.github.diegopacheco.scalaplayground

import language.experimental.saferExceptions

class CustomException(msg: String) extends Exception(msg)

def riskyOperation(x: Int): Int throws CustomException =
  if (x < 0) then
    throw CustomException("Negative value not allowed")
  else
    x * 2

def safeWrapper(x: Int)(using CanThrow[CustomException]): Int throws CustomException =
  try
    riskyOperation(x)
  catch
    case e: CustomException =>
      println(s"Caught exception: ${e.getMessage}")
      throw e

//
// https://docs.scala-lang.org/scala3/reference/experimental/canthrow.html
//
object CanThrowExperimentalApp extends App:
    try
      val result = safeWrapper(-5)
      println(s"Result: $result")
    catch
      case e: CustomException =>
        println(s"Handled exception in main: ${e.getMessage}")

    //
    // uncomment to see the compile error
    //
    /*
    * The capability to throw exception com.github.diegopacheco.scalaplayground.CustomException is missing.
      The capability can be provided by one of the following:
       - Adding a using clause `(using CanThrow[com.github.diegopacheco.scalaplayground.CustomException])` to the definition of the enclosing method
       - Adding `throws com.github.diegopacheco.scalaplayground.CustomException` clause after the result type of the enclosing method
       - Wrapping this piece of code with a `try` block that catches com.github.diegopacheco.scalaplayground.CustomException

      The following import might fix the problem:

        import unsafeExceptions.canThrowAny

     */
    //riskyOperation(-1)