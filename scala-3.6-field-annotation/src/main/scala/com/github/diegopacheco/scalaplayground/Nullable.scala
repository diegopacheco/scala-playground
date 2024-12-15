package com.github.diegopacheco.scalaplayground

import scala.annotation.meta.field

/**
 * Nullable annotation
 * */
@field
class Nullable extends scala.annotation.StaticAnnotation {
  def this(value: String) = this()
}