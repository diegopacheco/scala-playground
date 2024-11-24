package com.github.diegopacheco.scalaplayground.db

object DBCodeGenRunner extends App {
    slick.codegen.SourceCodeGenerator.main(
    Array(
      "slick.jdbc.PostgresProfile",
      "org.postgresql.Driver",
      "jdbc:postgresql://localhost:5433/computers",
      "db/src/main/scala",
      "com.github.diegopacheco.scalaplayground.db.slick",
      "postgres",
      "pass"
    )
  )
}