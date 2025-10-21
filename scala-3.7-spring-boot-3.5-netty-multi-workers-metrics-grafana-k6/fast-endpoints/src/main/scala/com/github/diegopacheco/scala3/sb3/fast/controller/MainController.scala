package com.github.diegopacheco.scala3.sb3.fast.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.bind.annotation.RequestMethod.GET

import scala.jdk.CollectionConverters.MapHasAsJava

@RestController
class MainController {

  @Value("${application.name}")
  val appName: String = "spring-app"

  @RequestMapping(path = Array("/"), method = Array(GET))
  def index(): java.util.Map[String, Any] = Map("name" -> appName,
    "message" -> "It works on my machine!"
  ).asJava

}
