package com.github.diegopacheco.slick.h2.infra

import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcProfile

object DatabaseConfig {
  private val config = ConfigFactory.load()

  def getProfile(profileName: String): JdbcProfile = {
    profileName match {
      case "h2" => slick.jdbc.H2Profile
      case "postgres" => slick.jdbc.PostgresProfile
      case _ => throw new IllegalArgumentException(s"Unknown profile: $profileName")
    }
  }

  def getDbConfig(profileName: String) = {
    val profile = getProfile(profileName)
    val dbConfig = config.getConfig(s"slick.profiles.$profileName.db")
    (profile, dbConfig)
  }
  
}