package com.github.diegopacheco.scala3.sb3.controller.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {

  @Bean
  def restTemplate(): RestTemplate = new RestTemplate()

}
