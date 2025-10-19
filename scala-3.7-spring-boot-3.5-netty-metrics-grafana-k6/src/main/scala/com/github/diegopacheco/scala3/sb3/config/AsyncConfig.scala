package com.github.diegopacheco.scala3.sb3.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig {

  @Bean(name = Array("healthCheckExecutor"))
  def healthCheckExecutor(): Executor = {
    val executor = new ThreadPoolTaskExecutor()
    executor.setCorePoolSize(4)
    executor.setMaxPoolSize(8)
    executor.setQueueCapacity(100)
    executor.setThreadNamePrefix("health-check-")
    executor.setWaitForTasksToCompleteOnShutdown(true)
    executor.setAwaitTerminationSeconds(60)
    executor.initialize()
    executor
  }
}
