package com.github.diegopacheco.scala3x.playground.pratical

//
// https://docs.scala-lang.org/scala3/reference/metaprogramming/inline.html
//
// Inline Definitions
//
// Inline methods are methods that are expanded at compile time.
// This means that the method body is copied to the call site.
// This can improve performance by avoiding method calls and
// allowing the compiler to optimize the code.
//
object InlineDefinitions extends App:

  // Inline Methods
  inline def square(x: Int): Int = x * x
  val x = square(5)
  println(x)

  // Inline Values
  inline val pi: 3.14159 = 3.14159
  println(pi)

  // Inline Parameters
  inline def power(inline base: Int, exponent: Int): Int =
    if exponent == 0 then 1
    else base * power(base, exponent - 1)

  val result = power(2, 3)
  println(result)

  inline def isEven(x: Int): Boolean = inline if x % 2 == 0 then true else false
  val resultIn = isEven(4) // The conditional is evaluated at compile time
  println(resultIn)