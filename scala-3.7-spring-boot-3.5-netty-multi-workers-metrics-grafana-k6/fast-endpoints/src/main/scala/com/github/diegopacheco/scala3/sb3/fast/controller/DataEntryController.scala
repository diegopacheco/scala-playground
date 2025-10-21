package com.github.diegopacheco.scala3.sb3.fast.controller

import com.github.diegopacheco.scala3.sb3.core.model.DataEntry
import com.github.diegopacheco.scala3.sb3.core.service.DataEntryService
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import scala.jdk.CollectionConverters.*

@RestController
class DataEntryController(private val service: DataEntryService, private val meterRegistry: MeterRegistry) {

  @GetMapping(path = Array("/create/{n}"))
  def createEntries(@PathVariable n: Int): ResponseEntity[java.util.List[DataEntry]] = {
    val entries = service.createRandomEntries(n)
    ResponseEntity.ok(entries.asJava)
  }

  @GetMapping(path = Array("/retrieve"))
  def getAllEntries(): ResponseEntity[java.util.List[DataEntry]] = {
    val entries = service.getAllEntries()
    ResponseEntity.ok(entries.asJava)
  }

  @GetMapping(path = Array("/retrieve/{id}"))
  def getEntryById(@PathVariable id: Long): ResponseEntity[?] = {
    service.getEntryById(id) match {
      case Some(entry) => ResponseEntity.ok(entry)
      case None => ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found")
    }
  }

  @GetMapping(path = Array("/prometheus"), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  def prometheus(): String = {
    val sb = new StringBuilder()
    meterRegistry.getMeters.asScala.foreach { meter =>
      val baseName = meter.getId.getName.replace(".", "_").replace("-", "_")
      val tags = meter.getId.getTags.asScala.map(tag => s"""${tag.getKey.replace(".", "_").replace("-", "_")}="${tag.getValue}"""").mkString(",")
      val tagsStr = if (tags.nonEmpty) s"{$tags}" else ""

      meter.measure().asScala.foreach { measurement =>
        val statName = measurement.getStatistic.toString.toLowerCase
        val metricName = statName match {
          case "value" => baseName
          case "count" if baseName.endsWith("_seconds") => baseName + "_count"
          case "count" => baseName + "_total"
          case "total_time" if baseName.endsWith("_seconds") => baseName + "_sum"
          case "total_time" => baseName + "_seconds_sum"
          case "total" => baseName + "_total"
          case "max" if baseName.endsWith("_seconds") => baseName + "_max"
          case "max" => baseName + "_max"
          case _ => s"${baseName}_${statName}"
        }
        sb.append(s"$metricName$tagsStr ${measurement.getValue}\n")
      }
    }
    sb.toString()
  }
}
