package com.github.diegopacheco.scala3.sb3.core.service

import com.github.diegopacheco.scala3.sb3.core.model.DataEntry
import com.github.diegopacheco.scala3.sb3.core.repository.DataEntryRepository
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.util.UUID
import scala.jdk.CollectionConverters.*

@Service
class DataEntryService(private val repository: DataEntryRepository) {

  def createRandomEntries(n: Int): List[DataEntry] = {
    (1 to n).map { _ =>
      val entry = DataEntry(
        null,
        s"entry-${UUID.randomUUID().toString}",
        s"value-${UUID.randomUUID().toString}",
        LocalDateTime.now()
      )
      repository.save(entry)
    }.toList
  }

  def getAllEntries(): List[DataEntry] = {
    repository.findAll().asScala.toList
  }

  def getEntryById(id: Long): Option[DataEntry] = {
    val result = repository.findById(id)
    if (result.isPresent) Some(result.get()) else None
  }
}
