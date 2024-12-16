package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.core.convert.converter.Converter
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.stereotype.Component
import scala.jdk.CollectionConverters._

@Component
class SpringDataConverter extends AbstractJdbcConfiguration {
  override def jdbcCustomConversions(): JdbcCustomConversions = {
    new JdbcCustomConversions(List(
      new SomeToStringConverter(),
      new StringToOptionConverter()
    ).asJava)
  }
}

class SomeToStringConverter extends Converter[Some[?], String] {
  override def convert(source: Some[?]): String = source.get.toString
}

class StringToOptionConverter extends Converter[String,Option[?]] {
  override def convert(source:String): Option[?] = Option(source.trim)
}