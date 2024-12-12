package com.github.diegopacheco.scalaplayground.conversions

import java.util.Optional
import scala.jdk.OptionConverters._

object ConversionsOps {
  given [T]: Conversion[Option[T], Optional[T]] with
    def apply(opt: Option[T]): Optional[T] = opt.toJava

  given [T]: Conversion[Optional[T], Option[T]] with
    def apply(opt: Optional[T]): Option[T] = opt.toScala

}