package com.github.diegopacheco.scala3.sb3.metrics

import io.micrometer.core.instrument.{Gauge, MeterRegistry}
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.stereotype.Component
import org.apache.tomcat.util.threads.ThreadPoolExecutor

@Component
class TomcatMetrics(
  registry: MeterRegistry,
  applicationContext: ServletWebServerApplicationContext
) extends ApplicationListener[ApplicationReadyEvent] {

  override def onApplicationEvent(event: ApplicationReadyEvent): Unit = {
    try {
      val webServer = applicationContext.getWebServer

      webServer match {
        case tomcatServer: TomcatWebServer =>
          val tomcat = tomcatServer.getTomcat
          val connectors = tomcat.getService.findConnectors()

          connectors.foreach { connector =>
            val protocolHandler = connector.getProtocolHandler
            val executor = protocolHandler.getExecutor

            if (executor != null && executor.isInstanceOf[ThreadPoolExecutor]) {
              val threadPool = executor.asInstanceOf[ThreadPoolExecutor]
              val connectorName = s"http-nio-${connector.getPort}"

              Gauge.builder("tomcat.threads.current", threadPool, tp => tp.getPoolSize.toDouble)
                .tag("name", connectorName)
                .description("Current thread count in Tomcat thread pool")
                .register(registry)

              Gauge.builder("tomcat.threads.busy", threadPool, tp => tp.getActiveCount.toDouble)
                .tag("name", connectorName)
                .description("Busy thread count in Tomcat thread pool")
                .register(registry)

              Gauge.builder("tomcat.threads.config.max", threadPool, tp => tp.getMaximumPoolSize.toDouble)
                .tag("name", connectorName)
                .description("Maximum thread count configured for Tomcat thread pool")
                .register(registry)

              println(s"✓ Registered Tomcat metrics for connector: $connectorName")
            }
          }
        case _ =>
          println("✗ Not a Tomcat server")
      }
    } catch {
      case e: Exception =>
        println(s"✗ Error registering Tomcat metrics: ${e.getMessage}")
        e.printStackTrace()
    }
  }
}
