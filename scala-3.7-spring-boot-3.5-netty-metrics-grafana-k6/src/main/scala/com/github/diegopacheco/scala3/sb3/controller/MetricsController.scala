package com.github.diegopacheco.scala3.sb3.controller

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{GetMapping, RestController}

import scala.jdk.CollectionConverters._

@RestController
class MetricsController(private val meterRegistry: MeterRegistry) {

  @GetMapping(path = Array("/actuator/prometheus"), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  def prometheus(): String = {
    val sb = new StringBuilder()

    meterRegistry.getMeters.asScala.foreach { meter =>
      val name = meter.getId.getName.replace(".", "_").replace("-", "_")
      val tags = meter.getId.getTags.asScala.map(tag => s"""${tag.getKey}="${tag.getValue}"""").mkString(",")
      val tagsStr = if (tags.nonEmpty) s"{$tags}" else ""

      meter.measure().asScala.foreach { measurement =>
        val metricName = s"${name}_${measurement.getStatistic.toString.toLowerCase}"
        sb.append(s"$metricName$tagsStr ${measurement.getValue}\n")
      }
    }

    sb.toString()
  }
}
