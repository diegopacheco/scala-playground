package com.github.diegopacheco.slick.postgres.api;

import org.json4s.native.Serialization
import org.json4s.{DefaultFormats, Formats, FullTypeHints, ShortTypeHints}

trait Json4sSupport {
  private val customTypeHints = FullTypeHints(List(
    classOf[ComputerModel],
  ))
  implicit val formats: Formats = DefaultFormats.withHints(customTypeHints)
  implicit val serialization: Serialization.type = Serialization
}