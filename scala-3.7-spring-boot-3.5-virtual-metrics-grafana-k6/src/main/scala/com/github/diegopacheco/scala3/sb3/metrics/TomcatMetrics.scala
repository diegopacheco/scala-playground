package com.github.diegopacheco.scala3.sb3.metrics

import io.micrometer.core.instrument.{Gauge, MeterRegistry}
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer
import org.springframework.boot.web.server.WebServer
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.stereotype.Component

@Component
class TomcatMetrics(
  registry: MeterRegistry,
  applicationContext: ServletWebServerApplicationContext
) extends ApplicationListener[ApplicationReadyEvent] {

  override def onApplicationEvent(event: ApplicationReadyEvent): Unit = {
    val webServer: WebServer = applicationContext.getWebServer

    webServer match {
      case tomcatServer: TomcatWebServer =>
        val tomcat = tomcatServer.getTomcat
        val connectors = tomcat.getService.findConnectors()

        connectors.foreach { connector =>
          val protocolHandler = connector.getProtocolHandler
          val executor = protocolHandler.getExecutor

          if (executor != null) {
            val connectorName = connector.getPort.toString

            Gauge.builder("tomcat.threads.current", executor, e => {
              try {
                val poolSizeMethod = e.getClass.getMethod("getPoolSize")
                poolSizeMethod.invoke(e).asInstanceOf[Int].toDouble
              } catch {
                case _: Exception => 0.0
              }
            })
            .tag("name", s"http-nio-$connectorName")
            .description("Current thread count in Tomcat thread pool")
            .register(registry)

            Gauge.builder("tomcat.threads.busy", executor, e => {
              try {
                val activeCountMethod = e.getClass.getMethod("getActiveCount")
                activeCountMethod.invoke(e).asInstanceOf[Int].toDouble
              } catch {
                case _: Exception => 0.0
              }
            })
            .tag("name", s"http-nio-$connectorName")
            .description("Busy thread count in Tomcat thread pool")
            .register(registry)

            Gauge.builder("tomcat.threads.config.max", executor, e => {
              try {
                val maxThreadsMethod = e.getClass.getMethod("getMaxThreads")
                maxThreadsMethod.invoke(e).asInstanceOf[Int].toDouble
              } catch {
                case _: Exception => 0.0
              }
            })
            .tag("name", s"http-nio-$connectorName")
            .description("Maximum thread count configured for Tomcat thread pool")
            .register(registry)
          }
        }
      case _ =>
    }
  }
}
