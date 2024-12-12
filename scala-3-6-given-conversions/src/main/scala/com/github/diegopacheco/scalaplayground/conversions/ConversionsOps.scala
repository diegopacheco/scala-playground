package com.github.diegopacheco.scalaplayground.conversions

import java.util.Optional
import scala.jdk.OptionConverters._

object ConversionsOps {
  extension [T](opt: Option[T])
    def toOptional: Optional[T] = opt.toJava

  extension [T](opt: Optional[T])
    def toOption: Option[T] = opt.toScala
}