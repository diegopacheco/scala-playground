package com.github.diegopacheco.scalaplayground.springboot.dao

import com.github.diegopacheco.scalaplayground.springboot.model.{Person, Person2}
import com.github.diegopacheco.scalaplayground.springboot.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DBLoader(
  @Autowired val jdbcTemplate: JdbcTemplate,
  @Autowired val service:PersonService
) {

  def insertData(): Unit = {
    try {
      jdbcTemplate.execute("INSERT INTO person(first_name,last_name) VALUES('Victor', 'Hugo')")
      jdbcTemplate.execute("INSERT INTO person(first_name,last_name) VALUES('Dante', 'Alighieri')")
      jdbcTemplate.execute("INSERT INTO person(first_name,last_name) VALUES('Stefan', 'Zweig')")
      jdbcTemplate.execute("INSERT INTO person(first_name,last_name) VALUES('Oscar', 'Wilde')")

      val p = new Person(firstName =  Option("Diego"), lastName =  Option("Pacheco"))
      service.save(p)
      println(s"Saved: Person ${p}")

      val p2 = new Person2(firstName =  Option("Diego"), lastName =  Option("Pacheco"))
      service.save2(p2)
      println(s"Saved: Person2 ${p}")
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}