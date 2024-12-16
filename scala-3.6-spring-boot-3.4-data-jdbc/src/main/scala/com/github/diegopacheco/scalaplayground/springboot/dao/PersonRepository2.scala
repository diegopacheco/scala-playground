package com.github.diegopacheco.scalaplayground.springboot.dao

import com.github.diegopacheco.scalaplayground.springboot.model.Person2
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
trait PersonRepository2 extends CrudRepository[Person2.PersonMapping, Long] {
  def findByFirstName(firstName: String): List[Person2]

  @Modifying
  @Query("UPDATE person SET first_name = :name WHERE id = :id")
  def updateByFirstName(@Param("id") id: Long, @Param("name") name: String): Boolean
}