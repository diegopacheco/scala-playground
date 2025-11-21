package com.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.{RouterFunction, ServerResponse, RouterFunctions}
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.jvm.*
import io.micrometer.core.instrument.binder.system.*

@SpringBootApplication
class Application:

  @Bean
  def routes(): RouterFunction[ServerResponse] =
    RouterFunctions.route(GET("/health"), _ => ok().bodyValue("OK"))

  @Bean
  def jvmMetrics(registry: MeterRegistry): String =
    new JvmMemoryMetrics().bindTo(registry)
    new JvmGcMetrics().bindTo(registry)
    new JvmThreadMetrics().bindTo(registry)
    new JvmHeapPressureMetrics().bindTo(registry)
    new ProcessorMetrics().bindTo(registry)
    new FileDescriptorMetrics().bindTo(registry)
    new UptimeMetrics().bindTo(registry)
    "jvmMetrics"

@main def main(args: String*): Unit =
  SpringApplication.run(classOf[Application], args*)
