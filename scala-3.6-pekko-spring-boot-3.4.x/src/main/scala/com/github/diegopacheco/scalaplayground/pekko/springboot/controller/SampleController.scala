package com.github.diegopacheco.scalaplayground.pekko.springboot.controller

import javax.annotation.PostConstruct
import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.javadsl.Source
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import org.springframework.util.Assert

@RestController
class SampleController @Autowired()(
  @Value("${org.apache.pekko.stream.connectors.spring.web.actor-system-name}") val actorSystemName: String
){
  @Autowired
  private val system: ActorSystem = null

  @GetMapping(Array("/actorSystemName"))
  def getActorSystemName: String = actorSystemName

  @RequestMapping(Array("/"))
  def index: Source[String, NotUsed] =
    Source.repeat("Hello world!").intersperse("\n").take(10)

  @PostConstruct
  def setup(): Unit = {
    val log = system.log
    log.info("Injected ActorSystem Name -> {}", system.name)
    log.info("Property ActorSystemName -> {}", actorSystemName)
    Assert.isTrue(system.name == actorSystemName, "Validating ActorSystem name")
  }
}