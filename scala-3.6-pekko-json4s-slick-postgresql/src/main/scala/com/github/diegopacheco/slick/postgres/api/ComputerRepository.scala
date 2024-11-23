package com.github.diegopacheco.slick.postgres.api

import slick.jdbc.PostgresProfile.api.*
import com.github.diegopacheco.slick.postgres.infra.Tables.*
import scala.concurrent.{ExecutionContext, Future}

class ComputerRepository(db: Database)(implicit ec: ExecutionContext) {
  def getAllComputers: Future[Seq[ComputerModel]] = {
    val query = Computers.result
    val action = query.map(_.map(row => ComputerModel(row.id, row.name, row.manufacturerId)))
    db.run(action)
  }
}