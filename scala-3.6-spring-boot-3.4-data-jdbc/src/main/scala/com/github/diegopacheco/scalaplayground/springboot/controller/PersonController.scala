package com.github.diegopacheco.scalaplayground.springboot.controller

import com.github.diegopacheco.scalaplayground.springboot.model.Person
import com.github.diegopacheco.scalaplayground.springboot.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.github.diegopacheco.scalaplayground.springboot.model

@RestController
class PersonController(
  @Autowired var service: PersonService
) {

  @RequestMapping(Array("/"))
  def index = "Greetings from Spring Boot!"

  @RequestMapping(Array("/all"))
  def getAllThePeople: List[Person] = service.getAllPeople
}