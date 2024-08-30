package com.github.diegopacheco.scala3.sb3.config

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.{Bean, Configuration, EnableAspectJAutoProxy}

@Configuration
@EnableAspectJAutoProxy
class MetricsConfig {
  @Bean
  def timedAspect(registry: MeterRegistry) = new TimedAspect(registry)
}
