package com.github.diegopacheco.scala3.sb3.metrics

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.stereotype.Component
import reactor.netty.ReactorNetty

@Component
class ReactorNettyMetrics extends MeterBinder {
  override def bindTo(registry: MeterRegistry): Unit = {
    registry.gauge("reactor.netty.ioWorkerCount",
      Integer.parseInt(System.getProperty(ReactorNetty.IO_WORKER_COUNT, "4")))
    registry.gauge("reactor.netty.udp.ioThreadCount",
      Integer.parseInt(System.getProperty(ReactorNetty.UDP_IO_THREAD_COUNT, "4")))
    registry.gauge("reactor.netty.pool.maxConnections",
      Integer.parseInt(System.getProperty(ReactorNetty.POOL_MAX_CONNECTIONS, "16")))
  }
}
