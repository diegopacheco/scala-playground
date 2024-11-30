package com.github.diegopacheco.scalatraining.db.codegen

object DBCodeGenRunner extends App {
  slick.codegen.SourceCodeGenerator.main(
    Array(
      "slick.jdbc.PostgresProfile",
      "org.postgresql.Driver",
      "jdbc:postgresql://localhost:5433/vending",
      "src/main/scala",
      "com.github.diegopacheco.scalatraining.db.slick",
      "postgres",
      "pass"
    )
  )
}