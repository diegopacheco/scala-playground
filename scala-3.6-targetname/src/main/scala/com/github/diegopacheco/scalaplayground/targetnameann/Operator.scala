package com.github.diegopacheco.scalaplayground.targetnameann

import scala.annotation.targetName

class Operator {
  @targetName("star")
  def * = "Scala Star Method"
}