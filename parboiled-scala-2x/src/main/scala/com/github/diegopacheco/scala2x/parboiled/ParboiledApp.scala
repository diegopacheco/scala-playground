package com.github.diegopacheco.scala2x.parboiled

import org.parboiled.scala._

class SimpleCalculator extends Parser {
  def Expression: Rule0 = rule { Term ~ zeroOrMore(anyOf("+-") ~ Term) }
  def Term = rule { Factor ~ zeroOrMore(anyOf("*/") ~ Factor) }
  def Factor = rule { Number | "(" ~ Expression ~ ")" }
  def Number = rule { oneOrMore("0" - "9") }
}

object ParboiledApp extends App{
  val input = "1+2"
  val parser = new SimpleCalculator { override val buildParseTree = true }
  val result = ReportingParseRunner(parser.Expression).run(input)
  val parseTreePrintOut = org.parboiled.support.ParseTreeUtils.printNodeTree(result)
  println(parseTreePrintOut)
}
