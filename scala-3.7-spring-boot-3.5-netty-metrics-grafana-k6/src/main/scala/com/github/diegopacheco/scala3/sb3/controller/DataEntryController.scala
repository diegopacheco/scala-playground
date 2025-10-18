package com.github.diegopacheco.scala3.sb3.controller

import com.github.diegopacheco.scala3.sb3.model.DataEntry
import com.github.diegopacheco.scala3.sb3.service.DataEntryService
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import scala.jdk.CollectionConverters.*

@RestController
class DataEntryController(private val service: DataEntryService) {

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
}
