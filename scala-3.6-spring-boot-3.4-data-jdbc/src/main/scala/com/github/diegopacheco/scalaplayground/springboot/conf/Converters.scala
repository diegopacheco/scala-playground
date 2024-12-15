package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.{Converter, ConverterFactory, GenericConverter}
import org.springframework.stereotype.Component
import java.util
import java.util.Collections

@Component
class StringToOptionConverterFactory extends ConverterFactory[String, Option[String]] {
  override def getConverter[T <: Option[String]](targetType: Class[T]): Converter[String, T] = {
    new StringToOptionConverter(targetType)
  }

  private class StringToOptionConverter[T <: Option[String]](private var optionType: Class[T]) extends Converter[String, T] {
    override def convert(source: String): T = {
      Option(source.trim).asInstanceOf[T]
    }
  }
}

@Component
class StringToOptionConverter extends Converter[String, Option[String]] {
  override def convert(source: String): Option[String] = Option(source)
}

@Component
class StringToOptionGenericConverter extends GenericConverter {

  override def getConvertibleTypes: util.Set[GenericConverter.ConvertiblePair] = {
    val pairs = new util.HashSet[GenericConverter.ConvertiblePair]()
    pairs.add(new GenericConverter.ConvertiblePair(classOf[String], classOf[Option[String]]))
    Collections.unmodifiableSet(pairs)
  }

  override def convert(source: Any, sourceType: TypeDescriptor, targetType: TypeDescriptor): AnyRef = {
    source match {
      case s: String => Option(s)
      case _ => Option.empty
    }
  }
}