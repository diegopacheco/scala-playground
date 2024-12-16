package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.{Converter, ConverterFactory, GenericConverter}
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.data.convert.{CustomConversions, ReadingConverter, WritingConverter}
import org.springframework.data.relational.core.conversion.MappingRelationalConverter
import org.springframework.data.relational.core.mapping.RelationalMappingContext
import org.springframework.format.support.FormattingConversionService
import org.springframework.stereotype.Component
import java.util
import java.util.{Arrays, Collections}

//
// Not working with spring data core and rest part
//

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

/*
@Component
class StringToOptionConverter extends Converter[String, Option[String]] {
  override def convert(source: String): Option[String] = Option(source)
}
*/

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

//@ReadingConverter
@WritingConverter
class SomeToStringConverter extends Converter[Some[?], String] {
  override def convert(source: Some[?]): String = source.get.toString
}

@ReadingConverter
class StringToOptionConverter extends Converter[String,Option[?]] {
  override def convert(source:String): Option[?] = Option(source.trim)
}

@Component
class CustomMappingRelationalConverter(context: RelationalMappingContext)
  extends MappingRelationalConverter(
    context,
    new CustomConversions(
      CustomConversions.StoreConversions.NONE,
      util.Arrays.asList(
        new SomeToStringConverter()
      )
    )
  )

@Configuration
class ConvertersConfig {

  @Bean
  def conversionService(): FormattingConversionService = {
    val conversionService = new FormattingConversionService()
    DefaultConversionService.addDefaultConverters(conversionService)
    conversionService.addConverter(new StringToOptionConverter())
    conversionService.addConverter(new SomeToStringConverter())
    conversionService
  }

  @Bean
  def defaultConversionService(): DefaultConversionService = {
    val conversionService = new DefaultConversionService()
    DefaultConversionService.addDefaultConverters(conversionService)
    conversionService.addConverter(new StringToOptionConverter())
    conversionService.addConverter(new SomeToStringConverter())
    conversionService
  }

  @Bean
  def customConversions(cs:DefaultConversionService): CustomConversions = {
    val cc = new CustomConversions(
      CustomConversions.StoreConversions.NONE,
      java.util.Arrays.asList(
        new SomeToStringConverter()
      )
    )
    cc.registerConvertersIn(cs)
    cc
  }

  @Bean
  def someToStringConverter(): SomeToStringConverter =
    new SomeToStringConverter()

  @Bean
  def stringToOptionGenericConverter(): StringToOptionGenericConverter =
    new StringToOptionGenericConverter()

  @Bean
  def stringToOptionConverterFactory(): StringToOptionConverterFactory =
    new StringToOptionConverterFactory()

}