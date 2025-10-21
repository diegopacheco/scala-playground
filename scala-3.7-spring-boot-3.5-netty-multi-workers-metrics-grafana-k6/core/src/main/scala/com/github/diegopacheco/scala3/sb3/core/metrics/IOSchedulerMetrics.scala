package com.github.diegopacheco.scala3.sb3.core.metrics

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.stereotype.Component
import reactor.core.scheduler.Schedulers

@Component
class IOSchedulerMetrics extends MeterBinder {
  override def bindTo(registry: MeterRegistry): Unit = {
    val boundedElasticScheduler = Schedulers.boundedElastic()
    Schedulers.enableMetrics()
  }
}
