package com.github.diegopacheco.scalaplayground.targetnameann

import scala.annotation.targetName

class TypeErasure {
  @targetName("totalLen")
  def sum(str: List[String]): Int = str.map(_.length).sum
}