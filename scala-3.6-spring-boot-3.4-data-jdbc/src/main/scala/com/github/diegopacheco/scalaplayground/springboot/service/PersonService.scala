package com.github.diegopacheco.scalaplayground.springboot.service

import com.github.diegopacheco.scalaplayground.springboot.dao.{PersonRepository, PersonRepository2}
import com.github.diegopacheco.scalaplayground.springboot.model.{Person, Person2}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.jdk.CollectionConverters.*

@Service
class PersonService(
  @Autowired val repository: PersonRepository,
  @Autowired val repository2: PersonRepository2
) {

  def getAllPeople: List[Person] = {
    repository.findAll.asScala.
      flatMap(personMapping => List(personMapping: Person)).toList
  }

  def getAllPeople2: List[Person] = {
    repository2.findAll.asScala.
      flatMap(personMapping => List(personMapping: Person)).toList
  }

  def save(p: Person): Unit = repository.save(p)
  def save2(p: Person2): Unit = repository2.save(p)
}
