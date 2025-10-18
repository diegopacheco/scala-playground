package com.github.diegopacheco.scala3.sb3.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}
import java.util

@RestController
class TestController:

  @GetMapping(path = Array("/test/prometheus-class"))
  def testPrometheusClass(): util.Map[String, Any] =
    try
      val clazz = Class.forName("io.micrometer.prometheusmetrics.PrometheusMeterRegistry")
      util.Map.of("status", "success", "class", clazz.getName)
    catch
      case e: Exception =>
        util.Map.of("status", "error", "message", e.getMessage, "type", e.getClass.getName)
