package com.github.diegopacheco.scala3.sb3.controller

import com.github.diegopacheco.scala3.sb3.model.DataEntry
import com.github.diegopacheco.scala3.sb3.service.DataEntryService
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import scala.jdk.CollectionConverters.*

@RestController
class DataEntryController(private val service: DataEntryService) {

  @Autowired(required = true)
  private var meterRegistry: MeterRegistry = _

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
