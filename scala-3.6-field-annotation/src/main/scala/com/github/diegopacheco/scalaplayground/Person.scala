package com.github.diegopacheco.scalaplayground

import scala.annotation.meta.field

class Person(@(JavaAnnotation @field) @Nullable var name: String) {
  override def toString: String = s"Person($name)"
}