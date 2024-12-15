package com.github.diegopacheco.scalaplayground.springboot.service

import com.github.diegopacheco.scalaplayground.springboot.dao.PersonRepository
import com.github.diegopacheco.scalaplayground.springboot.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util

@Service
class PersonService(
  @Autowired val repository: PersonRepository
) {

  def getAllPeople: util.List[Person] = {
    val result = new util.ArrayList[Person]
    repository.findAll.forEach(person => result.add(Person.fromSpring(person)))
    result
  }

  def save(p: Person): Unit = {
    repository.save(Person.toSpring(p))
  }
}
