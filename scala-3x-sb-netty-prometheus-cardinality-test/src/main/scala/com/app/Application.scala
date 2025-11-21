package com.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.reactive.function.server.{RouterFunction, RouterFunctions, ServerResponse}
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import reactor.netty.resources.{LoopResources, ConnectionProvider}
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.jvm.*
import io.micrometer.core.instrument.binder.system.*
import scala.compiletime.uninitialized

@SpringBootApplication
class Application:

  @Value("${netty.threshold.x}")
  private var THRESHOLD_X: Int = uninitialized

  @Bean
  def routes(): RouterFunction[ServerResponse] =
    RouterFunctions.route(GET("/health"), _ => ok().bodyValue("OK"))
      .andRoute(GET("/user/{id}"), request => {
        val userId = request.pathVariable("id")
        ok().bodyValue(s"User: $userId")
      })
      .andRoute(GET("/product/{id}"), request => {
        val productId = request.pathVariable("id")
        ok().bodyValue(s"Product: $productId")
      })
      .andRoute(GET("/order/{id}"), request => {
        val orderId = request.pathVariable("id")
        ok().bodyValue(s"Order: $orderId")
      })

  @Bean
  def nettyCustomizer(registry: MeterRegistry): WebServerFactoryCustomizer[NettyReactiveWebServerFactory] =
    factory => {
      System.setProperty("reactor.netty.ioWorkerCount", THRESHOLD_X.toString)
      println(s"Configuring Netty with THRESHOLD_X = $THRESHOLD_X worker threads")
      val loopResources = LoopResources.create("http-epoll", THRESHOLD_X, true)
      val connectionProvider = ConnectionProvider.builder("custom")
        .maxConnections(THRESHOLD_X)
        .pendingAcquireMaxCount(THRESHOLD_X * 2)
        .build()

      factory.addServerCustomizers(server => {
        server
          .runOn(loopResources)
          .metrics(true, java.util.function.Function.identity[String]())
          .doOnConnection(conn => {
            println(s"Connection established on thread: ${Thread.currentThread().getName}")
          })
      })
    }

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
