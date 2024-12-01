package com.github.diegopacheco.scalap99

// P47 (*) Truth tables for logical expressions (2).
//     Continue problem P46 by redefining and, or, etc as operators.  (i.e. make
//     them methods of a new class with an implicit conversion from Boolean.)
//     not will have to be left as a object method.
//
//     scala> table2((a: Boolean, b: Boolean) => a and (a or not(b)))
//     A     B     result
//     true  true  true
//     true  false true
//     false true  false
//     false false false
// For simplicity, we remove `and`, `or`, `equ`, `xor`, `nor`, `nand`, and
object P47{
  import com.github.diegopacheco.scalap99.P47.*
  import com.github.diegopacheco.scalap99.P47.LogicOps.*

  class LogicOps(val a: Boolean):
    def and(b: Boolean): Boolean = (a, b) match
      case (true, true) => true
      case _ => false

    def or(b: Boolean): Boolean = (a, b) match
      case (false, false) => false
      case _ => true

    def equ(b: Boolean): Boolean = (a and b) or (not(a) and not(b))
    def xor(b: Boolean): Boolean = not(a equ b)
    def nor(b: Boolean): Boolean = not(a or b)
    def nand(b: Boolean): Boolean = not(a and b)
    def impl(b: Boolean): Boolean = not(a) or b

  object LogicOps:
    implicit def booleanToLogicOps(a: Boolean): LogicOps = new LogicOps(a)

  def not(a: Boolean): Boolean = !a

  def table2(f: (Boolean, Boolean) => Boolean): Unit =
    println("A     B     result")
    for {a <- List(true, false)
         b <- List(true, false)}
      printf("%-5s %-5s %-5s\n", a, b, f(a, b))

}

object P47Main extends App:
  import P47.*
  import P47.LogicOps.*
  P47.table2((a, b) => a and (a or not(b)))