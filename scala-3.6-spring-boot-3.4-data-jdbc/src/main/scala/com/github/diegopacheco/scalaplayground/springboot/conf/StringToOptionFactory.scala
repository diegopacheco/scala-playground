package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.core.convert.converter.{Converter, ConverterFactory}
import org.springframework.stereotype.Component

@Component
class StringToOptionConverterFactory extends ConverterFactory[String, Option[_]] {
  override def getConverter[T <: Option[_]](targetType: Class[T]): Converter[String, T] = {
    new StringToOptionConverter(targetType)
  }

  private class StringToOptionConverter[T <: Option[_]](private var optionType: Class[T]) extends Converter[String, T] {
    override def convert(source: String): T = {
      Option(source.trim).asInstanceOf[T]
    }
  }
}

@Component
class StringToOptionConverter extends Converter[String, Option[String]] {
  override def convert(source: String): Option[String] = Option(source)
}