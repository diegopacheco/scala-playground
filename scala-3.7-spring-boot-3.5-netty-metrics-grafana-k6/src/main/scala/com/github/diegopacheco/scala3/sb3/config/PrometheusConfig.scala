package com.github.diegopacheco.scala3.sb3.config

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class PrometheusConfiguration:

  @Bean
  def meterRegistryCustomizer(): MeterRegistryCustomizer[MeterRegistry] =
    new MeterRegistryCustomizer[MeterRegistry]:
      override def customize(registry: MeterRegistry): Unit =
        registry.config().commonTags("application", "scala-3.7-spring-boot-3.5-netty-metrics-grafana-k6-app")

  @Bean
  def prometheusMeterRegistry(): MeterRegistry =
    try
      val configClass = Class.forName("io.micrometer.prometheusmetrics.PrometheusConfig")
      val defaultField = configClass.getField("DEFAULT")
      val defaultConfig = defaultField.get(null)

      val registryClass = Class.forName("io.micrometer.prometheusmetrics.PrometheusMeterRegistry")
      val constructor = registryClass.getConstructor(configClass)
      constructor.newInstance(defaultConfig).asInstanceOf[MeterRegistry]
    catch
      case e: Exception =>
        println(s"Failed to create PrometheusMeterRegistry: ${e.getMessage}")
        e.printStackTrace()
        null
