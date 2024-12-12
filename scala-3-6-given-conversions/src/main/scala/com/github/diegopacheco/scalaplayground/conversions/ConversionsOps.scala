package com.github.diegopacheco.scalaplayground.conversions

import java.util.Optional
import scala.jdk.OptionConverters.*
import scala.language.implicitConversions

object ConversionsOps {

  // for scala <-> scala

  given [T]: Conversion[Option[T], Optional[T]] with
    def apply(opt: Option[T]): Optional[T] = opt.toJava

  given [T]: Conversion[Optional[T], Option[T]] with
    def apply(opt: Optional[T]): Option[T] = opt.toScala

  // for scala <-> java

  implicit def optionIntToOptionalInteger(opt: Option[Int]): Optional[Integer] =
      opt.map(Int.box).toJava

  implicit def optionalIntegerToOptionInt(opt: Optional[Integer]): Option[Int] =
    opt.toScala.map(Int.unbox)
}