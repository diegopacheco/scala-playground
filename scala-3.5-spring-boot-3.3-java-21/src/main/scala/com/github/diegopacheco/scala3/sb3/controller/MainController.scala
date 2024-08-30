package com.github.diegopacheco.scala3.sb3.controller

import com.github.diegopacheco.scala3.sb3.service.TimeService
import io.micrometer.core.annotation.Timed
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.bind.annotation.RequestMethod.GET

import scala.jdk.CollectionConverters.MapHasAsJava

@RestController
class MainController {

  @Value("${application.name}")
  val appName: String = "spring-app"

  @Autowired
  val timeService: TimeService = null

  @Timed(value = "main.controller.index", description = "Time taken to return index")
  @RequestMapping(path = Array("/"), method = Array(GET))
  def index(): java.util.Map[String, Any] = Map("name" -> appName,
    "message" -> "It works on my machine!",
    "time" -> timeService.getTime
  ).asJava

}
