package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.core.convert.TypeDescriptor
import org.springframework.format.support.FormattingConversionService
import org.springframework.core.convert.converter.{Converter, GenericConverter}
import org.springframework.stereotype.Component
import java.util
import java.util.{Collections, HashSet}
import scala.Option

@Component
class StringToOptionGenericConverter extends GenericConverter {

  override def getConvertibleTypes: util.Set[GenericConverter.ConvertiblePair] = {
    val pairs = new util.HashSet[GenericConverter.ConvertiblePair]()
    pairs.add(new GenericConverter.ConvertiblePair(classOf[String], classOf[Option[?]]))
    Collections.unmodifiableSet(pairs)
  }

  override def convert(source: Any, sourceType: TypeDescriptor, targetType: TypeDescriptor): AnyRef = {
    source match {
      case s: String => Option(s)
      case _ => Option.empty
    }
  }
}

class StringToOptionConverter extends Converter[String, Option[String]] {
  override def convert(source: String): Option[String] = Option(source)
}

@Configuration
class ConverterConfig {

  @Bean(name = Array("conversionService"))
  def conversionService(): FormattingConversionService = {
    val conversionService = new FormattingConversionService()
    conversionService.addConverter(new StringToOptionConverter())
    conversionService.addConverter(new StringToOptionGenericConverter())
    conversionService
  }

}