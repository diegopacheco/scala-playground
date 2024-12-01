package com.github.diegopacheco.scalap99

// P48 - Truth tables for logical expressions (3)
//
// Generalize problem P47 in such a way that the logical expression may contain any
// number of logical variables. Define table in a way that (table List Expr) prints the
// truth table for the expression Expr, which contains the logical variables enumerated in List.
//
// > true.xor_(true, false, true)
// true
object P48:
  import P47.*
  import P47.LogicOps.*

  extension (a: Boolean)
    def xor_(b: Boolean*): Boolean = b.foldLeft(a)((acc,el) => acc xor el)

  def table(expr: List[Boolean], f: List[Boolean] => Boolean): Unit =
    println("A     B     result")
    for {a <- List(true, false)
         b <- List(true, false)}
      printf("%-5s %-5s %-5s\n", a, b, f(List(a,b)))

object P48Main extends App:
  import P48.*
  import P47.*
  import P47.LogicOps.*
  P48.table(List(true, false), (l) => l(0) and (l(0) or not(l(1))))