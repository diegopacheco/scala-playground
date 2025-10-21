package com.github.diegopacheco.scala3.sb3.health

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import reactor.core.scheduler.Schedulers

@SpringBootApplication
@ComponentScan(Array("com.github.diegopacheco.scala3.sb3.core.config", "com.github.diegopacheco.scala3.sb3.core.health", "com.github.diegopacheco.scala3.sb3.core.metrics", "com.github.diegopacheco.scala3.sb3.health"))
class HealthCheckerApplication

object HealthCheckerApplication {
  def main(args: Array[String]): Unit = {
    Schedulers.enableMetrics()
    SpringApplication.run(classOf[HealthCheckerApplication])
  }
}
