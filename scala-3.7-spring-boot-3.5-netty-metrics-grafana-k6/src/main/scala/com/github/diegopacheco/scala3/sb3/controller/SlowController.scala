package com.github.diegopacheco.scala3.sb3.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import scala.jdk.CollectionConverters.*

@RestController
class SlowController {

  @GetMapping(path = Array("/slow/{timeSeconds}"))
  def slowEndpoint(@PathVariable timeSeconds: Int): ResponseEntity[java.util.Map[String, Any]] = {
    val startTime = System.currentTimeMillis()
    Thread.sleep(timeSeconds * 1000L)
    val endTime = System.currentTimeMillis()

    ResponseEntity.ok(Map(
      "message" -> s"Slept for $timeSeconds seconds",
      "requestedSeconds" -> timeSeconds,
      "actualTimeMs" -> (endTime - startTime)
    ).asJava)
  }
}
