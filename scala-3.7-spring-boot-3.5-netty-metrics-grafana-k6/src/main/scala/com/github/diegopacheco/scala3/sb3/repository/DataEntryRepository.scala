package com.github.diegopacheco.scala3.sb3.repository

import com.github.diegopacheco.scala3.sb3.model.DataEntry
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait DataEntryRepository extends CrudRepository[DataEntry, java.lang.Long]
