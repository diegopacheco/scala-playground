package com.github.diegopacheco.scalaplayground.springboot.service

import com.github.diegopacheco.scalaplayground.springboot.dao.PersonRepository
import com.github.diegopacheco.scalaplayground.springboot.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.mutable.ListBuffer

@Service
class PersonService(
  @Autowired val repository: PersonRepository
) {

  def getAllPeople: List[Person] = {
    val result = ListBuffer[Person]()
    repository.findAll.forEach(person => result += Person.fromDB(person) )
    result.toList
  }

  def save(p: Person): Unit =
    repository.save(Person.toDB(p))
}
