package com.github.diegopacheco.scalaplayground.springboot.dao

import com.github.diegopacheco.scalaplayground.springboot.model.Person
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import java.util

@Repository
trait PersonRepository extends CrudRepository[Person, Long] {
  def findByFirstName(firstName: String): util.List[Person]

  @Modifying
  @Query("UPDATE person SET first_name = :name WHERE id = :id")
  def updateByFirstName(@Param("id") id: Long, @Param("name") name: String): Boolean
}
