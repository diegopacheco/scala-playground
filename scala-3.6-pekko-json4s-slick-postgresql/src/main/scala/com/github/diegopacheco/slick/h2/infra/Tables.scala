package com.github.diegopacheco.slick.h2.infra

import slick.jdbc.H2Profile
import demo.Tables

object Tables extends Tables  {
  // or just use object demo.Tables, which is hard-wired to the driver stated during generation
  override val profile: H2Profile.type = slick.jdbc.H2Profile
}
