package com.github.diegopacheco.scalaplayground.springboot.service

import com.github.diegopacheco.scalaplayground.springboot.dao.PersonRepository
import com.github.diegopacheco.scalaplayground.springboot.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.jdk.CollectionConverters._

@Service
class PersonService(
  @Autowired val repository: PersonRepository
) {

  def getAllPeople: List[Person] = {
    repository.findAll.asScala.
      flatMap(personMapping => List(personMapping: Person)).toList
  }

  def save(p: Person): Unit =
    repository.save(p)
}
