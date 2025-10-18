package com.github.diegopacheco.scala3.sb3.controller

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class PrometheusController {

  @Autowired(required = false)
  var meterRegistry: MeterRegistry = _

  @GetMapping(path = Array("/actuator/prometheus"), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  def prometheus(): String = {
    if (meterRegistry != null && meterRegistry.getClass.getName.contains("Prometheus")) {
      val scrapeMethod = meterRegistry.getClass.getMethod("scrape")
      scrapeMethod.invoke(meterRegistry).asInstanceOf[String]
    } else {
      "# Prometheus metrics not available"
    }
  }
}
