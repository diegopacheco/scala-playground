package com.github.diegopacheco.scalaplayground.conversions

import java.util.Optional
import scala.jdk.OptionConverters._

object ConversionsOps {
  given Conversion[Option[Int], Optional[Int]] with
    def apply(opt: Option[Int]): Optional[Int] = opt.toJava

  given Conversion[Optional[Int], Option[Int]] with
    def apply(opt: Optional[Int]): Option[Int] = opt.toScala
}