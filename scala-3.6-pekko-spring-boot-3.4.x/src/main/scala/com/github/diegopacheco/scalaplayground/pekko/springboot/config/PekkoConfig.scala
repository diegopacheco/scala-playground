package com.github.diegopacheco.scalaplayground.pekko.springboot.config

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.connectors.spring.web.SpringWebPekkoStreamsProperties
import org.springframework.boot.autoconfigure.condition.{ConditionalOnClass, ConditionalOnMissingBean}
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{Bean, Configuration}
import org.apache.pekko.stream.scaladsl.Source
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ReactiveAdapterRegistry

import java.util.Objects

@Configuration
@ConditionalOnClass(Array(classOf[Source[?, ?]]))
@EnableConfigurationProperties(Array(classOf[SpringWebPekkoStreamsProperties]))
class PekkoConfig (
  @Autowired() val properties: SpringWebPekkoStreamsProperties
) {
  private val DEFAULT_FACTORY_SYSTEM_NAME = "PekkoSpringWebPekkoStreamsSystem"

  val registry: ReactiveAdapterRegistry = ReactiveAdapterRegistry.getSharedInstance
  val system: ActorSystem = ActorSystem.create(getActorSystemName(properties))

  @Bean
  @ConditionalOnMissingBean(Array(classOf[ActorSystem]))
  def getActorSystem: ActorSystem = system

  def getProperties: SpringWebPekkoStreamsProperties = properties

  private def getActorSystemName(properties: SpringWebPekkoStreamsProperties):
  String = {

    Objects.requireNonNull(properties,
      String.format("%s is not present in application context",
      classOf[SpringWebPekkoStreamsProperties].getSimpleName))

    if (isBlank(properties.getActorSystemName))
      return DEFAULT_FACTORY_SYSTEM_NAME
    properties.getActorSystemName
  }

  private def isBlank(str: String) = str == null || str.isEmpty
}