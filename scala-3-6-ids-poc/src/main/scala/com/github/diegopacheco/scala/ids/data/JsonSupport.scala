package com.github.diegopacheco.scala.ids.data

import org.json4s._
import org.json4s.native.Serialization

trait JsonSupport {
  implicit val formats: Formats = new DefaultFormats {
    override val typeHints: TypeHints = DefaultFormats.typeHints
  }
  implicit val serialization: Serialization.type = Serialization
}
