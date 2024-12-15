package com.github.diegopacheco.scalaplayground.springboot.conf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class JacksonConfig {

  @Bean(Array("objectMapper"))
  def objectMapper(): ObjectMapper = {
    val mapper = new ObjectMapper()
    mapper.registerModule(new DefaultScalaModule())
    mapper
  }

}
