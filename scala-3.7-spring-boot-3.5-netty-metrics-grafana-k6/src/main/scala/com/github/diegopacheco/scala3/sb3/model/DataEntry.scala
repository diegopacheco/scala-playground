package com.github.diegopacheco.scala3.sb3.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

import java.time.LocalDateTime
import scala.beans.BeanProperty

@Table("data_entries")
case class DataEntry(
  @Id @BeanProperty var id: java.lang.Long,
  @BeanProperty var name: String,
  @BeanProperty var value: String,
  @BeanProperty var createdAt: LocalDateTime
) {
  def this() = this(null, null, null, null)
}
