package com.github.diegopacheco.scala3.sb3.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.{Executor, Executors}

@Configuration
@EnableAsync
class AsyncConfig {

  @Bean(name = Array("healthCheckExecutor"))
  def healthCheckExecutor(): Executor = {
    Executors.newVirtualThreadPerTaskExecutor()
  }

  @Bean(name = Array("slowRequestExecutor"))
  def slowRequestExecutor(): Executor = {
    Executors.newVirtualThreadPerTaskExecutor()
  }
}
