package com.github.scalaplayground.lib

import org.json4s._
import org.json4s.native.Serialization

trait Json4sSupport {
  implicit val formats: Formats = new DefaultFormats {
    override val typeHints: TypeHints = DefaultFormats.typeHints
  }
  implicit val serialization: Serialization.type = Serialization
}
