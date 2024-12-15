package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.context.support.ConversionServiceFactoryBean
import org.springframework.core.convert.{ConversionService, TypeDescriptor}
import org.springframework.core.convert.converter.{Converter, GenericConverter}
import org.springframework.data.convert.ValueConverter
import java.util
import java.util.{Collections, HashSet}
import scala.Option

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

  @Bean
  def conversionService(factory: ConversionServiceFactoryBean): ConversionService =
    factory.getObject

  @Bean
  def conversionServiceFactoryBean(converters: java.util.Set[Converter[?, ?]]): ConversionServiceFactoryBean = {
    val factory = new ConversionServiceFactoryBean
    converters.add(new StringToOptionConverter())
    factory.setConverters(converters)
    factory
  }

  @Bean
  def StringToOptionGenericConverter:StringToOptionGenericConverter =
    new StringToOptionGenericConverter

}