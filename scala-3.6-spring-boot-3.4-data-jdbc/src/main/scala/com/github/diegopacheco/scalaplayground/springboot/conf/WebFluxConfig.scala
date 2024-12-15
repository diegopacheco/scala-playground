package com.github.diegopacheco.scalaplayground.springboot.conf

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebFluxConfig extends WebFluxConfigurer {
  override def addFormatters(registry: FormatterRegistry): Unit = {
    registry.addConverter(new StringToOptionConverter)
    registry.addConverter(new StringToOptionGenericConverter)
    registry.addConverter(new SomeToStringConverter)
    registry.addConverterFactory(new StringToOptionConverterFactory)
  }
}
