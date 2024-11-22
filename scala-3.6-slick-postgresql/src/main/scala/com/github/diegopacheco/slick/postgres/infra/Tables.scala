package com.github.diegopacheco.slick.postgres.infra

import slick.jdbc.PostgresProfile

object Tables extends demo.Tables {
  // or just use object demo.Tables, which is hard-wired to the driver stated during generation
  override val profile: PostgresProfile.type = slick.jdbc.PostgresProfile
}
